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

'Vérifier que l\'on est redirigé vers la page d\'accueil'
WebUI.waitForElementPresent(findTestObject('Page d accueil/Section Mes activités/Colonne Activité'), 10)

'Vérifier l\'apparition de l\'activité qui vient d\'être crée '
WebUI.verifyElementText(findTestObject('Page d accueil/Section Mes activités/Colonne Activité'), 'Sim Swap')

'Vérifier que le statut est fermé'
WebUI.verifyElementText(findTestObject('Page d accueil/Section Mes activités/Colonne Statut'), 'Fermé')

String dateCreationActivite = WebUI.getText(findTestObject('Page d accueil/Section Mes activités/Colonne Date'))

dateCreationActivite = dateCreationActivite.substring(0, 14)

'Vérifier que la date du traitement est conforme'
WebUI.verifyMatch(dateCreationActivite, dateHeureTraitement, false)

WebUI.verifyElementText(findTestObject('Page d accueil/Section Mes activités/Colonne numero'), numeroActivite)

'Saisir un numéro que l\'on vient de Sim swaper dans le moteur de recherche'
WebUI.sendKeys(findTestObject('Header/Moteur de recherche'), GlobalVariable.serviceActifSwap1)

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

    if (typeService.equals('Service Mobile / Actif') && numeroAppel.equals(GlobalVariable.serviceActifSwap1)) {
        type.click()

        isfound = true
    }
}

WebUI.waitForElementPresent(findTestObject('Page Service/Fil ariane Service'), 5)

'Vérifier la présence du fil d\'ariane avec le numéro du service séléctionné'
WebUI.verifyElementText(findTestObject('Page Service/Fil ariane Service'), 'Service ' + numero)

'Vérifier que le MSISDN dans la page est la même que celui du service séléctionné'
WebUI.verifyElementText(findTestObject('Page Service/Section Info service/info MSISDN'), GlobalVariable.serviceActifSwap1)

'Vérifier que l\' ICCID du SIM est celui du nouvell ICCID'
WebUI.verifyElementText(findTestObject('Page Service/Section Info service/info SIM'), GlobalVariable.nouvelICCIDSwap1)

