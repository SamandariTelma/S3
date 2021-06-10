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

'Initialisation JDD'
String numeroActivite = '677076101'

String typeActivite = 'Activité Choix de Numéro'

String nomCompte = 'TNRSND'

'Se connecter à S3'
WebUI.callTestCase(findTestCase('00-Called Test Case/Connexion à S3'), [:], FailureHandling.CONTINUE_ON_FAILURE)

'Vérifier que la présence du champ moteur de recherche'
WebUI.waitForElementPresent(findTestObject('Header/Moteur de recherche'), 10)

'Saisir le numéro de l\'activité à rechercher'
WebUI.sendKeys(findTestObject('Header/Moteur de recherche'), numeroActivite)

WebUI.click(findTestObject('Header/Bouton loupe'))

WebUI.waitForElementVisible(findTestObject('Page de résultat moteur de recherche/Titre de la page'), 15)

WebUI.verifyElementText(findTestObject('Page de résultat moteur de recherche/Titre de la page'), ('Résultat de la recherche pour "' + 
    numeroActivite) + '"')

'Vérifier que le résultat correspont bien à la recherche'
WebUI.verifyElementText(findTestObject('Page de résultat moteur de recherche/Colonne type'), typeActivite)

WebUI.verifyElementText(findTestObject('Page de résultat moteur de recherche/Colonne numéro'), numeroActivite)

WebUI.verifyElementText(findTestObject('Page de résultat moteur de recherche/Colonne compte'), nomCompte)

