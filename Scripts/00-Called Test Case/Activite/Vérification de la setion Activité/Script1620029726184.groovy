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

'Vérifier l\' apparition du lien avec le numero client'
WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Lien avec numero client'), GlobalVariable.numeroCompte)

'Vérifier la présence du bouton Recharger et Commande'
WebUI.verifyElementPresent(findTestObject('Page Activité/Page activité (commun)/Bouton Recharger'), 3)

WebUI.verifyElementPresent(findTestObject('Page Activité/Page activité (commun)/Bouton Commande'), 3)

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Bouton Recharger'), 'Recharger')

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Bouton Commande'), 'Commande')

'Vérifier le titre de la section'
WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Section Activite/Titre'), 'Activité')

'Vérifier l\'apparition des libelé suivants:\r\n- Statut\r\n- Priorité\r\n- Canal\r\n- Assigné à\r\n- Créé le\r\n- Modifié le'
WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Section Activite/Label Statut'), 'Statut')

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Section Activite/Label Priorite'), 'Priorité')

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Section Activite/Label Canal'), 'Canal')

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Section Activite/Label Assigne a'), 'Assigné à')

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Section Activite/Label Cree le'), 'Créé le')

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Section Activite/Label Modifie le'), 'Modifié le')

'Vérifier la valeur correspondante à chaque libelé'
WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Section Activite/Statut activite'), 'En cours')

WebUI.verifyElementAttributeValue(findTestObject('Page Activité/Page activité (commun)/Section Activite/Champ Priorite activite'), 
    'value', 'NORMAL', 3)

WebUI.verifyElementAttributeValue(findTestObject('Page Activité/Page activité (commun)/Section Activite/Champ canal'), 'value', 
    'CALL', 3)

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Section Activite/Responsable activite'), (GlobalVariable.titulaireCompte + 
    '/') + GlobalVariable.typeCompte)

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Section Activite/Createur activite'), GlobalVariable.titulaireCompte)

dateCrea = WebUI.getText(findTestObject('Page Activité/Page activité (commun)/Section Activite/Date creation activite'))

String regex = '^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{2} (2[0-3]|[01]?[0-9]):([0-5]?[0-9]):([0-5]?[0-9])$'

WebUI.verifyMatch(dateCrea, regex, true)

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Section Activite/Modifieur du compte'), 'Admin User')

dateModif = WebUI.getText(findTestObject('Page Activité/Page activité (commun)/Section Activite/Date de modification'))

WebUI.verifyMatch(dateModif, regex, true)

