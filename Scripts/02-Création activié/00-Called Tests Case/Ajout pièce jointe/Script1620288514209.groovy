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

'Cliquer sur l\'onglet pièces jointes'
WebUI.click(findTestObject('Page Activité/Page activité (commun)/Section Onglets/Onglet Piece jointe'))

WebUI.delay(0.500)

'Cliquer sur le bouton Nouveau'
WebUI.click(findTestObject('Page Activité/Page activité (commun)/Section Onglets/Bouton Nouveau pièce jointe'))

WebUI.waitForElementPresent(findTestObject('Page Activité/Page activité (commun)/Section Onglets/Popin Pièce jointe/Titre'), 
    5)

'Vérifier le titre du popin'
WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Section Onglets/Popin Pièce jointe/Titre'), 
    'Upload')

'Cliquer sur la liste déroulante Type'
WebUI.click(findTestObject('Page Activité/Page activité (commun)/Section Onglets/Popin Pièce jointe/Champ type'))

'Choisir l\'option Justificatif d\'identité'
WebUI.click(findTestObject('Page Activité/Page activité (commun)/Section Onglets/Popin Pièce jointe/Option Justificatif d identite'))

WebUI.delay(0.500)

'Cliquer sur la liste déroulante Nom'
WebUI.click(findTestObject('Page Activité/Page activité (commun)/Section Onglets/Popin Pièce jointe/Champ Nom'))

'Choisir l\'option CIN recto'
WebUI.click(findTestObject('Page Activité/Page activité (commun)/Section Onglets/Popin Pièce jointe/Option CIN recto'))

'Télécharger le fichier image'
WebUI.uploadFile(findTestObject('Page Activité/Page activité (commun)/Section Onglets/Popin Pièce jointe/Champ telechargement piece jointe'), 
    'D:\\Utilisateurs\\samandari\\QA\\PROJET AUTOMATISATION\\S3\\S3\\Include\\JDD pj\\img test.PNG')

'Vérifier que le pièce jointes est ajouté dans le popin'
WebUI.verifyElementPresent(findTestObject('Page Activité/Page activité (commun)/Section Onglets/Popin Pièce jointe/Section piece jointe telechargé'), 
    10)

'Cliquer sur le bouton télécharger'
WebUI.click(findTestObject('Page Activité/Page activité (commun)/Section Onglets/Popin Pièce jointe/Bouton Telecharger'))

WebUI.waitForElementPresent(findTestObject('Page Activité/Page activité (commun)/Section Onglets/Onglet pieces jointes/Titre colonne Type'), 
    5)

//Récupération de la date et heure actuel
def date = new Date()

def sdf = new SimpleDateFormat('dd/MM/yy HH:mm')

String dateHeureTraitement = sdf.format(date)

'Vérifier la présence du tableu de pièce jointes avec les colonnes suivants:\r\n- Colonne Type\r\n- Colonne Description\r\n- Colonne Deposée par\r\n- Colonne Créé le'
WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Section Onglets/Onglet pieces jointes/Titre colonne Type'), 
    'Type')

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Section Onglets/Onglet pieces jointes/Titre colonne Description'), 
    'Description')

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Section Onglets/Onglet pieces jointes/Titre Colonne Deposee par'), 
    'Deposée par')

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Section Onglets/Onglet pieces jointes/Titre colonne Cree le'), 
    'Créé le')

'Vérifier que le tableau affiche le pièce jointe vennant d\'être télécharger:\r\n- Type: Justificatif d\'identité\r\n- Description: CIN recto\r\n- Déposé par: [nom user]\r\n- Créé le: date et heure de création\r\n- Bouton de supréssion '
WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Section Onglets/Onglet pieces jointes/Colonne type'), 
    'Justificatif d\'identité')

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Section Onglets/Onglet pieces jointes/Colonne description'), 
    'CIN recto')

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Section Onglets/Onglet pieces jointes/Colonne deposee par'), 
    GlobalVariable.loginUsername)

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Section Onglets/Onglet pieces jointes/Colonne cree le'), 
   dateHeureTraitement)

WebUI.verifyElementPresent(findTestObject('Page Activité/Page activité (commun)/Section Onglets/Onglet pieces jointes/Bouton de supression'), 
    3)

