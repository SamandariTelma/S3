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

WebUI.verifyElementText(findTestObject('Page Compte/Section Personne Morale/01-nom'), personneMorale.get('nom'))

WebUI.verifyElementText(findTestObject('Page Compte/Section Personne Morale/02-stat'), personneMorale.get('stat'))

WebUI.verifyElementText(findTestObject('Page Compte/Section Personne Morale/03-nif'), personneMorale.get('nif'))

WebUI.verifyElementText(findTestObject('Page Compte/Section Personne Morale/04-reference budgetaire'), personneMorale.get('referenceBudget'))

WebUI.verifyElementText(findTestObject('Page Compte/Section Personne Morale/05-mail'), personneMorale.get('mail'))

WebUI.verifyElementText(findTestObject('Page Compte/Section Personne Morale/06-tel'), personneMorale.get('tel'))

WebUI.verifyElementText(findTestObject('Page Compte/Section Personne Morale/07-rue'), personneMorale.get('rue'))

WebUI.verifyElementText(findTestObject('Page Compte/Section Personne Morale/08-adresse'), personneMorale.get('adresse'))

WebUI.verifyElementText(findTestObject('Page Compte/Section Personne Morale/09-province'), personneMorale.get('province'))

WebUI.verifyElementText(findTestObject('Page Compte/Section Personne Morale/10-region'), personneMorale.get('region'))

WebUI.verifyElementText(findTestObject('Page Compte/Section Personne Morale/11-district'), personneMorale.get('district'))

WebUI.verifyElementText(findTestObject('Page Compte/Section Personne Morale/12-commune'), personneMorale.get('commune'))

WebUI.verifyElementText(findTestObject('Page Compte/Section Personne Morale/13-fokotany'), personneMorale.get('fokotany'))

WebUI.verifyElementText(findTestObject('Page Compte/Section Personne Morale/14-code postal'), personneMorale.get('codePostal'))
