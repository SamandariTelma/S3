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

'Vérifier l\'apparition des éléments suivants sur le formulaire SIM Swap:\r\n- Titre Sim Swap\r\n- Bouton Action\r\n- Liste déroulante Service\r\n- Champ Ancien ICCID désactivé\r\n- Liste déroulante Sortie de SIM\r\n- Champ Numéro provisoire désactivé\r\n- Champ Nouvel ICCID\r\n- Liste déroulante Fiche de récupération\r\n- Liste déroulante Pièce justificative\r\n- Champ text Commentaires\r\n- bouton valider et annuler'
WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Formulaire SIM Swap/Titre SIM Swap'), 'Sim Swap')

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Label Service'), 
    'Service')

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Label Ancien ICCID'), 
    'Ancien ICCID')

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Label Sortie de SIM'), 
    'Sortie de SIM')

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Formulaire Récupération de Numéro/Label Numero provisoire'), 
    'Numéro provisoire')

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Formulaire Récupération de Numéro/Label Nouvel ICCID'), 
    'Nouvel ICCID')

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Formulaire Récupération de Numéro/Label Fiche de recuperation'), 
    'Fiche de récupération')

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Label Piece justificative'), 
    'Pièce justificative')

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Label Commentaires'), 
    'Commentaires')

WebUI.verifyElementPresent(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ Service'), 
    3)

WebUI.verifyElementPresent(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ Ancien ICCID'), 
    3)

WebUI.verifyElementAttributeValue(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ Ancien ICCID'), 
    'disabled', 'true', 3)

WebUI.verifyElementPresent(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ Sortie de SIM'), 
    3)

WebUI.verifyElementPresent(findTestObject('Page Activité/Page activité (commun)/Formulaire Récupération de Numéro/Champ numero provisoire'), 
    3)

WebUI.verifyElementAttributeValue(findTestObject('Page Activité/Page activité (commun)/Formulaire Récupération de Numéro/Champ numero provisoire'), 
    'disabled', 'true', 3)

WebUI.verifyElementPresent(findTestObject('Page Activité/Page activité (commun)/Formulaire Récupération de Numéro/Champ Nouvel ICCID'), 
    3)

WebUI.verifyElementPresent(findTestObject('Page Activité/Page activité (commun)/Formulaire Récupération de Numéro/Champ fiche de recuperation'), 
    3)

WebUI.verifyElementPresent(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ Piece justificative'), 
    3)

WebUI.verifyElementPresent(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ Commentaires'), 
    3)

WebUI.verifyElementPresent(findTestObject('Page Activité/Page activité (commun)/Bouton Action'), 3)

WebUI.verifyElementPresent(findTestObject('Page Activité/Page activité (commun)/Bouton Valider'), 3)

WebUI.verifyElementPresent(findTestObject('Page Activité/Page activité (commun)/Bouton Annuler'), 3)

