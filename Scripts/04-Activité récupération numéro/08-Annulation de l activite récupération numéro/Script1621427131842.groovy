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
import java.text.SimpleDateFormat as SimpleDateFormat

'Créer un activité récupération numéro'
WebUI.callTestCase(findTestCase('02-Création activié/03-Création activité récupération numéro'), [:], FailureHandling.CONTINUE_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Page d accueil/Section Mes activités/Colonne numero'), 5)

String numeroActivite = WebUI.getText(findTestObject('Page d accueil/Section Mes activités/Colonne numero'))

'Cliquer sur le numéro de l\'activité que l\'on vient de créer'
WebUI.click(findTestObject('Page d accueil/Section Mes activités/Colonne numero'))

WebUI.waitForElementPresent(findTestObject('Page Activité/Page activité (commun)/Fil ariane Activité'), 5)

numeroActiviteFilAriane = WebUI.getText(findTestObject('Page Activité/Page activité (commun)/Fil ariane Activité'))

'Vérifier que le numéro d\'activité cliquer est la même que celui de la page de l\'activité'
WebUI.verifyMatch(numeroActiviteFilAriane.substring(9), numeroActivite, false)

'Cliquer sur le bouton Annuler'
WebUI.click(findTestObject('Page Activité/Page activité (commun)/Bouton Annuler'))

'Vérifier l\'apparition du popin avec les éléments suivants:\r\n- Titre du popin\r\n- Message de confirmation\r\n- Bouton Oui et Non'
WebUI.waitForElementVisible(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Titre popin'), 5)

WebUI.verifyElementText(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Titre popin'), 'Confirmation')

WebUI.verifyElementPresent(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Message de confirmation'), 3)

WebUI.verifyElementText(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Message de confirmation'), 'Etes-vous sûr de vouloir annuler cette activité?')

WebUI.verifyElementPresent(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Bouton Oui'), 3)

WebUI.verifyElementPresent(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Bouton Non'), 3)

'Cliquer sur le bouton non'
WebUI.click(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Bouton Non'))

WebUI.delay(1)

'Vérifier que je reste sur la page de l\'activité en voyant les boutons Annuler et Valider'
WebUI.verifyElementPresent(findTestObject('Page Activité/Page activité (commun)/Bouton Annuler'), 3)

WebUI.verifyElementPresent(findTestObject('Page Activité/Page activité (commun)/Bouton Valider'), 3)

'Cliquer à nouveau sur le bouton Annuler'
WebUI.click(findTestObject('Page Activité/Page activité (commun)/Bouton Annuler'))

WebUI.delay(0.500)

'Cliquer sur le bouton oui'
WebUI.click(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Bouton Oui'))

//Récupération de la date et heure actuel
def date = new Date()

def sdf = new SimpleDateFormat('dd/MM/yy HH:mm')

String dateHeureTraitement = sdf.format(date)

'Vérifier que l\'on est redirigé vers la page d\'accueil'
WebUI.waitForElementPresent(findTestObject('Page d accueil/Section Mes activités/Colonne Activité'), 10)

WebUI.verifyMatch(WebUI.getUrl(), GlobalVariable.url + 'activity/index.xhtml', false)

'Vérifier l\'apparition de l\'activité qui vient d\'être annulé'
WebUI.verifyElementText(findTestObject('Page d accueil/Section Mes activités/Colonne Activité'), 'Récupération de Numéro')

'Vérifier que le statut est devenu Annulé'
WebUI.verifyElementText(findTestObject('Page d accueil/Section Mes activités/Colonne Statut'), 'Annulé')

String dateAnnulationActivite = WebUI.getText(findTestObject('Page d accueil/Section Mes activités/Colonne Date'))

dateAnnulationActivite = dateAnnulationActivite.substring(0, 14)

'Vérifier que la date du traitement est conforme'
WebUI.verifyMatch(dateAnnulationActivite, dateHeureTraitement, false)

WebUI.verifyElementText(findTestObject('Page d accueil/Section Mes activités/Colonne numero'), numeroActivite)

