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

'Vérifier l\'apparition des éléments suivants dans le formulaire choix de numéro:\r\n- Titre: Choix de numéro\r\n- Champ Service\r\n- Champ Sortie SIM\r\n- Champ Ancien ICCID\r\n- Champ numéro souhaité\r\n- Bouton vérifier\r\n- Champ Pièces justificative\r\n- Champ Contact client\r\n- Champ Commentaire'
WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Titre Choix de numero'), 
    'Choix de Numéro')

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Label Service'), 
    'Service')

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Label Sortie de SIM'), 
    'Sortie de SIM')

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Label Ancien ICCID'), 
    'Ancien ICCID')

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Label Numéro souhaite'), 
    'Numéro souhaité')

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Label Piece justificative'), 
    'Pièce justificative')

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Label Contact client'), 
    'Contact client')

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Label Commentaires'), 
    'Commentaires')

WebUI.verifyElementPresent(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ Service'), 
    3)

WebUI.verifyElementPresent(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ Service'), 
    3)

WebUI.verifyElementPresent(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Label Ancien ICCID'), 
    3)

WebUI.verifyElementAttributeValue(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ Ancien ICCID'), 
    'disabled', 'true', 3)

WebUI.verifyElementPresent(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ Numero souhaite'), 
    3)

WebUI.verifyElementPresent(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Bouton Vérifier'), 
    3)

WebUI.verifyElementPresent(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ Piece justificative'), 
    3)

WebUI.verifyElementPresent(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ contact client'), 
    3)

WebUI.verifyElementPresent(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ Commentaires'), 
    3)

WebUI.verifyElementPresent(findTestObject('Page Activité/Page activité (commun)/Bouton Valider'), 3)

WebUI.verifyElementAttributeValue(findTestObject('Page Activité/Page activité (commun)/Bouton Valider'), 'disabled', 'true', 
    3)

WebUI.verifyElementPresent(findTestObject('Page Activité/Page activité (commun)/Bouton Annuler'), 3)

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Tableau de numéro/Titre colonne numero d appel'), 
    'N° d\'appel(s) disponible(s)')

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Tableau de numéro/Titre colonne Catégorie'), 
    'Catégorie')

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Tableau de numéro/Titre colonne Prix'), 
    'Prix(Ar)')

