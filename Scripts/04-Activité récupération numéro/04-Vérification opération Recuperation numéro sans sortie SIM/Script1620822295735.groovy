import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import org.openqa.selenium.WebElement as WebElement
import java.text.SimpleDateFormat as SimpleDateFormat

/*numeroActivite = '3618043's

dateHeureTraitement = '06/05/21 14:45'

WebUI.callTestCase(findTestCase('00-Called Test Case/Connexion à S3'), [:], FailureHandling.CONTINUE_ON_FAILURE)*/
String numeroAResilie = "$numeroResilie"

String nouvelIccid = "$nouvelICCID"

WebUI.callTestCase(findTestCase('00-Called Test Case/Connexion à S3'), [:], FailureHandling.CONTINUE_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Header/Moteur de recherche'), 5)

'Saisir un numéro que l\'on vient de Sim swaper dans le moteur de recherche'
WebUI.sendKeys(findTestObject('Header/Moteur de recherche'), numeroAResilie)

'Cliquer sur le bouton loupe'
WebUI.click(findTestObject('Header/Bouton loupe'))

WebUI.delay(0.500)

'Vérifier la présence du numéro recherché dans le résultat'
WebUI.waitForElementPresent(findTestObject('Page de résultat moteur de recherche/Colonne type'), 10)

List<WebElement> colonneType = WebUiCommonHelper.findWebElements(findTestObject('Page de résultat moteur de recherche/Colonne type'), 
    5)

List<WebElement> colonneNumeroAppel = WebUiCommonHelper.findWebElements(findTestObject('Page de résultat moteur de recherche/Colonne numero d appel'), 
    5)

List<WebElement> colonneNumero = WebUiCommonHelper.findWebElements(findTestObject('Page de résultat moteur de recherche/Colonne numéro'), 
    5)

int resultSize = colonneType.size

boolean isfound = false

String numero

'Cliquer sur le numéro recherché'
for (i = 0; (i < resultSize) && (isfound == false); i++) {
    def type = colonneType.get(i)

    String typeService = type.getText()

    def numeroTel = colonneNumeroAppel.get(i)

    String numeroAppel = numeroTel.getText()

    def numeroService = colonneNumero.get(i)

    numero = numeroService.getText()

    if (typeService.equals('Service Mobile / Actif') && numeroAppel.equals(numeroAResilie)) {
        type.click()

        isfound = true
    }
}

WebUI.waitForElementPresent(findTestObject('Page Service/Fil ariane Service'), 5)

'Vérifier la présence du fil d\'ariane avec le numéro du service séléctionné'
WebUI.verifyElementText(findTestObject('Page Service/Fil ariane Service'), 'Service ' + numero)

'Vérifier que le MSISDN dans la page est la même que celui du service séléctionné'
WebUI.verifyElementText(findTestObject('Page Service/Section Info service/info MSISDN'), numeroAResilie)

'Vérifier que l\' ICCID du SIM est celui du nouvell ICCID'
WebUI.verifyElementText(findTestObject('Page Service/Section Info service/info SIM'), nouvelIccid)

'Vérifier que la date de réactivation correspond à la date actuel'
def date = new Date()

def sdf = new SimpleDateFormat('dd/MM/yyyy')

String dateReactivation = sdf.format(date)

WebUI.verifyElementText(findTestObject('Page Service/Section Info service/date de reactivation'), dateReactivation)

'Vérifier la présence du section info ligne'
WebUI.verifyElementPresent(findTestObject('Page Service/Section Info ligne/Titre Info ligne'), 3)

'Vérifier la présence du bouton Recharger dans la section Info ligne'
WebUI.verifyElementPresent(findTestObject('Page Service/Section Info ligne/Bouton Recharger'), 3)

'Cliquer sur le bouton Recharger'
WebUI.click(findTestObject('Page Service/Section Info ligne/Bouton Recharger'))

'Vérifier l\'apparition du libelé statut ligne'
WebUI.waitForElementVisible(findTestObject('Page Service/Section Info ligne/Libele Statut ligne'), 3)

WebUI.click(findTestObject('Page Compte/Fil ariane Compte1'))

WebUI.delay(0.500)

WebUI.verifyElementText(findTestObject('Page Compte/Onglet Aperçu/Titre Titulaire des services'), 'Titulaire des services')

'Vérifier si le service est présent dans l\'aperçu'
List<WebElement> numeroService = WebUiCommonHelper.findWebElements(findTestObject('Page Compte/Onglet Aperçu/reference de service'), 
    5)

List<WebElement> statutService = WebUiCommonHelper.findWebElements(findTestObject('Page Compte/Onglet Aperçu/statut service'), 
    5)

List<WebElement> msisdnService = WebUiCommonHelper.findWebElements(findTestObject('Page Compte/Onglet Aperçu/msisdn du service'), 
    5)

int resultServiceSize = numeroService.size

boolean isServicefound = false

for (i = 0; (i < resultServiceSize) && (isServicefound == false); i++) {
    def num = numeroService.get(i)

    String ref = num.getText()

    def statut = statutService.get(i)

    String status = statut.getText()

    def numTel = msisdnService.get(i)

    msisdn = numTel.getText()

    if ((ref.equals(numero) && status.equals('Actif')) && msisdn.equals(numeroAResilie)) {
        isServicefound = true

        println((((ref + '\n') + status) + '\n') + msisdn)
    }
}

WebUI.verifyEqual(isServicefound, true, FailureHandling.CONTINUE_ON_FAILURE)

