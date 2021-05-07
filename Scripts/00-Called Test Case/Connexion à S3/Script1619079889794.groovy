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

WebUI.callTestCase(findTestCase('00-Called Test Case/Ouvrir le navigateur'), [:], FailureHandling.CONTINUE_ON_FAILURE)

'Se rendre sur le site S3'
WebUI.navigateToUrl(GlobalVariable.url)

WebUI.waitForElementPresent(findTestObject('Page Login/Champ user'), 10)

'Saisir le bon login username et password'
WebUI.sendKeys(findTestObject('Page Login/Champ user'), GlobalVariable.loginUsername)

WebUI.sendKeys(findTestObject('Page Login/Champ password'), GlobalVariable.loginPassword)

'Cliquer sur le bouton connexion'
WebUI.click(findTestObject('Page Login/Bouton connexion'))

WebUI.waitForElementPresent(findTestObject('Header/Username header'), 10)

