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

/*'Prérequis: Création d\'un activité SIM Swap via un service actif'
WebUI.callTestCase(findTestCase('02-Création activié/08-Création activité SIM Swap via un service'), [:], FailureHandling.CONTINUE_ON_FAILURE)*/
WebUI.callTestCase(findTestCase('02-Création activié/08-Création activité SIM Swap via un service'), [:], FailureHandling.CONTINUE_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Page d accueil/Section Mes activités/Colonne numero'), 5)

String numeroActivite = WebUI.getText(findTestObject('Page d accueil/Section Mes activités/Colonne numero'))

'Cliquer sur le numéro de l\'activité que l\'on vient de créer'
WebUI.click(findTestObject('Page d accueil/Section Mes activités/Colonne numero'))

WebUI.waitForElementPresent(findTestObject('Page Activité/Page activité (commun)/Fil ariane Activité'), 5)

numeroActiviteFilAriane = WebUI.getText(findTestObject('Page Activité/Page activité (commun)/Fil ariane Activité'))

'Vérifier que le champ service est prérempli par le numéro de service'
WebUI.verifyMatch(numeroActiviteFilAriane.substring(9), numeroActivite, false)

'Vérifier que le champ ancien ICCID est prérempli par le numero ICCD du numéro actif'
WebUI.verifyElementAttributeValue(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ Service'), 
    'value', GlobalVariable.serviceActifSwap1, 3)

WebUI.verifyElementAttributeValue(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ Ancien ICCID'), 
    'value', GlobalVariable.ancienICCD, 3)

ancienICCID = WebUI.getAttribute(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ Ancien ICCID'), 
    'value')

'Choisir oui dans le champ sortie de SIM'
WebUI.click(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ Sortie de SIM'))

WebUI.delay(0.500)

WebUI.click(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Option Oui Sortie de SIM'))

'Saisir le nouvel ICCID'
WebUI.sendKeys(findTestObject('Page Activité/Page activité (commun)/Formulaire Récupération de Numéro/Champ Nouvel ICCID'), 
    GlobalVariable.nouvelICCIDSwap1)

'Choisir valide dans le champ fiche de récupération '
WebUI.click(findTestObject('Page Activité/Page activité (commun)/Formulaire Récupération de Numéro/Champ fiche de recuperation'))

WebUI.delay(0.500)

WebUI.click(findTestObject('Page Activité/Page activité (commun)/Formulaire Récupération de Numéro/Option Valide fiche de recuperation'))

WebUI.click(findTestObject('Page Activité/Page activité (commun)/Formulaire Récupération de Numéro/Champ fiche de recuperation'))

'Choisir valide dans le champ pièce justificative'
WebUI.click(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ Piece justificative'))

WebUI.delay(0.500)

WebUI.click(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Option Valide piece justificative'))

WebUI.click(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ Piece justificative'))

WebUI.sendKeys(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ Commentaires'), 'TEST')

'Cliquer sur le bouton valider'
WebUI.click(findTestObject('Page Activité/Page activité (commun)/Bouton Valider'))

'Vérifier l\'apparition du popin de confirmation avec les éléments suivants:\r\n- titre: Confirmation\r\n- message: Etes-vous sûr de vouloir clôturer cette activité?\r\n- Bouton oui et non\r\n'
WebUI.waitForElementVisible(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Titre popin'), 
    3)

WebUI.verifyElementText(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Titre popin'), 'Confirmation')

WebUI.verifyElementText(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Message de confirmation'), 
    'Etes-vous sûr de vouloir clôturer cette activité?')

WebUI.verifyElementPresent(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Bouton Non'), 3)

WebUI.verifyElementPresent(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Bouton Oui'), 3)

'Cliquer sur le bouton Oui du popin'
WebUI.click(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Bouton Oui'))

WebUI.waitForElementPresent(findTestObject('Page Activité/Page activité (commun)/Message d erreur orange'), 5)

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Message d erreur orange'), 'Aucune pièce jointe n’a été déposée pour cette activité')

'Ajouter une pièce jointe'
WebUI.callTestCase(findTestCase('02-Création activié/00-Called Tests Case/Ajout pièce jointe'), [:], FailureHandling.CONTINUE_ON_FAILURE)

WebUI.waitForElementClickable(findTestObject('Page Activité/Page activité (commun)/Bouton Valider'), 5)

'Cliquer sur le bouton valider'
WebUI.click(findTestObject('Page Activité/Page activité (commun)/Bouton Valider'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.delay(0.500)

'Vérifier l\'apparition du popin de confirmation avec les éléments suivants:\r\n- titre: Confirmation\r\n- message: Etes-vous sûr de vouloir clôturer cette activité?\r\n- Bouton oui et non\r\n'
WebUI.waitForElementVisible(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Titre popin'), 
    3)

'Cliquer sur le bouton Oui du popin'
WebUI.click(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Bouton Oui'))

WebUI.waitForElementPresent(findTestObject('Page Commande/Bouton Valider'), 20, FailureHandling.STOP_ON_FAILURE)

'Cliquer sur le bouton valider'
WebUI.click(findTestObject('Page Commande/Bouton Valider'), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Titre popin'), 
    5)

'Vérifier la conformité du popin'
WebUI.verifyElementText(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Titre popin'), 'Confirmation')

WebUI.verifyElementText(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Message de confirmation'), 
    'Etes-vous sûr de vouloir valider cette commande?')

'Cliquer sur le bouton Non'
WebUI.click(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Bouton Non'))

WebUI.delay(0.500)

'Cliquer à nouveau sur le bouton valider'
WebUI.click(findTestObject('Page Commande/Bouton Valider'))

//Récupération de la date et heure actuel
def date = new Date()

def sdf = new SimpleDateFormat('dd/MM/yy HH:mm')

String dateHeureTraitement = sdf.format(date)

WebUI.delay(0.500)

WebUI.waitForElementPresent(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Bouton Oui'), 5)

'Cliquer sur le bouton Oui'
WebUI.click(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Bouton Oui'), FailureHandling.CONTINUE_ON_FAILURE)

'Retourner sur la Home Page'
WebUI.click(findTestObject('Header/Menue Home Page S3'))

WebUI.delay(60)

WebUI.callTestCase(findTestCase('05-Activité SIM Swap/00-Called Test Case/Vérification opération SIM Swap'), [('numeroActivite') : numeroActivite
        , ('dateHeureTraitement') : dateHeureTraitement], FailureHandling.CONTINUE_ON_FAILURE)

