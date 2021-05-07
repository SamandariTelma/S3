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

'Se connecter à S3'
WebUI.callTestCase(findTestCase('00-Called Test Case/Connexion à S3'), [:], FailureHandling.CONTINUE_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Header/Menu client'), 10)

'Cliquer sur le menu Client'
WebUI.click(findTestObject('Header/Menu client'))

WebUI.waitForElementPresent(findTestObject('Page Client/Section mes comptes/Titre section mes comptes'), 5)

List<WebElement> colonneNumero = WebUiCommonHelper.findWebElements(findTestObject('Page Client/Section mes comptes/Colonne numéro'), 
    5)

'Rechercher le compte dans lequel on veut créer l\'activité puis cliquer dessus'
for (def numero : colonneNumero) {
    if (numero.getText().equals(GlobalVariable.numeroCompte)) {
        numero.click()
    }
}

WebUI.waitForElementPresent(findTestObject('Page Compte/Fil ariane Compte'), 3)

'Redirection vers la page du compte séléctionné, je vois le fil d\'ariane avec le numéro du compte'
WebUI.verifyElementText(findTestObject('Page Compte/Fil ariane Compte'), 'Compte ' + GlobalVariable.numeroCompte)

'Cliquer sur l\'onglet Activités'
WebUI.click(findTestObject('Page Compte/Onglet Activite/Onglet activité'))

WebUI.delay(0.500)

WebUI.waitForElementVisible(findTestObject('Page Compte/Onglet Activite/Bouton Nouveau'), 3)

'Clique sur le bouton Nouveau'
WebUI.click(findTestObject('Page Compte/Onglet Activite/Bouton Nouveau'), FailureHandling.CONTINUE_ON_FAILURE)

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
WebUI.waitForElementPresent(findTestObject('Page Activité/Page activité (commun)/Fil ariane Activité'), 5)

WebUI.verifyMatch(WebUI.getUrl(), GlobalVariable.url + 'activity/activity-view.xhtml', false)

String numeroActivite = WebUI.getText(findTestObject('Page Activité/Page activité (commun)/Fil ariane Activité'))

numeroActivite = numeroActivite.substring(9)

'Cliquer sur le menue header S3'
WebUI.click(findTestObject('Header/Menue Home Page S3'))

WebUI.delay(1)

WebUI.maximizeWindow()

'Vérifier que l\'on est redirigé vers la page d\'accueil'
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

