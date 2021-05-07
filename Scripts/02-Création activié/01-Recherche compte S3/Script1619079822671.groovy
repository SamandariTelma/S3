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


'Initialisation JDD'
String numeroCompte = GlobalVariable.numeroCompte

String typeCompte = 'Client Entreprise'

String nomCompte = 'TNRSND'

'Se connecter à S3'
WebUI.callTestCase(findTestCase('00-Called Test Case/Connexion à S3'), [:], FailureHandling.CONTINUE_ON_FAILURE)

'Vérifier que la présence du champ moteur de recherche'
WebUI.waitForElementPresent(findTestObject('Header/Moteur de recherche'), 10)

'Saisir le numéro de compte S3'
WebUI.sendKeys(findTestObject('Header/Moteur de recherche'), numeroCompte)

WebUI.click(findTestObject('Header/Bouton loupe'))

WebUI.waitForElementVisible(findTestObject('Page de résultat moteur de recherche/Titre de la page'), 15)

WebUI.verifyElementText(findTestObject('Page de résultat moteur de recherche/Titre de la page'), ('Résultat de la recherche pour "' + 
    numeroCompte) + '"')

'Vérifier que le résultat correspont bien à la recherche'
WebUI.verifyElementText(findTestObject('Page de résultat moteur de recherche/Colonne type'), typeCompte)

WebUI.verifyElementText(findTestObject('Page de résultat moteur de recherche/Colonne numéro'), numeroCompte, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementText(findTestObject('Page de résultat moteur de recherche/Colonne compte'), (('N° ' + numeroCompte) + 
    ' | Titulaire : ') + nomCompte)

