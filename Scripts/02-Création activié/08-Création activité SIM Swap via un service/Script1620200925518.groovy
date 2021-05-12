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

String serviceAswape="${serviceASwape}"
'Se connecter à S3'
WebUI.callTestCase(findTestCase('00-Called Test Case/Connexion à S3'), [:], FailureHandling.CONTINUE_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Header/Moteur de recherche'), 10)

'Saisir un numéro actif dans le moteur de recherche'
WebUI.sendKeys(findTestObject('Header/Moteur de recherche'), serviceASwape)

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

    if (typeService.equals('Service Mobile / Actif') && numeroAppel.equals(serviceASwape)) {
        type.click()

        isfound = true
    }
}

WebUI.waitForElementPresent(findTestObject('Page Service/Fil ariane Service'), 5)

'Vérifier la présence du fil d\'ariane avec le numéro du service séléctionné'
WebUI.verifyElementText(findTestObject('Page Service/Fil ariane Service'), 'Service ' + numero)

'Vérifier que le MSISDN dans la page est la même que celui du service séléctionné'
WebUI.verifyElementText(findTestObject('Page Service/Section Info service/info MSISDN'), serviceASwape)

GlobalVariable.ancienICCD=WebUI.getText(findTestObject('Page Service/Section Info service/info SIM'))

'Cliquer sur l\'onglet Activité'
WebUI.click(findTestObject('Page Service/Onglet Activité'))

WebUI.delay(0.500)

WebUI.waitForElementPresent(findTestObject('Page Service/Onglet activite/Bouton Nouveau'), 3)

'Cliquer sur le bouton Nouveau'
WebUI.click(findTestObject('Page Service/Onglet activite/Bouton Nouveau'), FailureHandling.CONTINUE_ON_FAILURE)

'Vérifier l\'apparition du popin Nouvelle Activité'
WebUI.waitForElementVisible(findTestObject('Page Compte/Onglet Activite/Popin Nouvelle Activité/Titre popin'), 3)

WebUI.verifyElementText(findTestObject('Page Compte/Onglet Activite/Popin Nouvelle Activité/Titre popin'), 'Nouvelle activité')

WebUI.verifyElementAttributeValue(findTestObject('Page Compte/Onglet Activite/Popin Nouvelle Activité/Bouton Contact Entrant'), 
    'value', 'Contact Entrant', 3)

'Cliquer sur le bouton Contact Entrant'
WebUI.click(findTestObject('Page Compte/Onglet Activite/Popin Nouvelle Activité/Bouton Contact Entrant'))

WebUI.waitForElementVisible(findTestObject('Page Compte/Onglet Activite/Popin Nouvelle Activité/Bouton Sim Swap'), 3)

WebUI.verifyElementAttributeValue(findTestObject('Page Compte/Onglet Activite/Popin Nouvelle Activité/Bouton Sim Swap'), 
    'value', 'Sim Swap', 3)

'Cliquer sur le bouton Sim Swap'
WebUI.click(findTestObject('Page Compte/Onglet Activite/Popin Nouvelle Activité/Bouton Sim Swap'))

//Récupération de la date et heure actuel
def date = new Date()

def sdf = new SimpleDateFormat('dd/MM/yy HH:mm')

String dateHeureTraitement = sdf.format(date)

'Vérifier que l\'on est redirigé vers la page de formulaire de l\'activité'
WebUI.waitForElementPresent(findTestObject('Page Service/Fil ariane Activité dans service'), 5)

WebUI.verifyMatch(WebUI.getUrl(), GlobalVariable.url + 'activity/activity-view.xhtml', false)

String numeroActivite = WebUI.getText(findTestObject('Page Service/Fil ariane Activité dans service'))

numeroActivite = numeroActivite.substring(9)

'Cliquer sur le menue header S3'
WebUI.click(findTestObject('Header/Menue Home Page S3'))

WebUI.delay(1)

WebUI.maximizeWindow()

'Vérifier que l\'on est redirigé vers la page d\'accueil'
WebUI.waitForPageLoad(60)
WebUI.waitForElementPresent(findTestObject('Page d accueil/Section Mes activités/Colonne Activité'), 10)

WebUI.verifyMatch(WebUI.getUrl(), GlobalVariable.url + 'activity/index.xhtml', false)

'Vérifier l\'apparition de l\'activité qui vient d\'être crée '
WebUI.verifyElementText(findTestObject('Page d accueil/Section Mes activités/Colonne Activité'), 'Sim Swap')

'Vérifier que le statut est en cours'
WebUI.verifyElementText(findTestObject('Page d accueil/Section Mes activités/Colonne Statut'), 'En cours')

String dateCreationActivite = WebUI.getText(findTestObject('Page d accueil/Section Mes activités/Colonne Date'))

dateCreationActivite = dateCreationActivite.substring(0, 14)

'Vérifier que la date du traitement est conforme'
WebUI.verifyMatch(dateCreationActivite, dateHeureTraitement, false)

WebUI.verifyElementText(findTestObject('Page d accueil/Section Mes activités/Colonne numero'), numeroActivite)

