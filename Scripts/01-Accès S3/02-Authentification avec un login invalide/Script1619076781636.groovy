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

String typeCompte = 'DSI_PROJET_S3'

WebUI.callTestCase(findTestCase('00-Called Test Case/Ouvrir le navigateur'), [:], FailureHandling.CONTINUE_ON_FAILURE)

'Se rendre sur le site S3'
WebUI.navigateToUrl(GlobalVariable.url)

WebUI.waitForElementPresent(findTestObject('Page Login/Champ user'), 10)

'Saisir un username inscrit sur S3'
WebUI.sendKeys(findTestObject('Page Login/Champ user'), GlobalVariable.loginUsername)

'Saisir un mauvais mot de passe'
WebUI.sendKeys(findTestObject('Page Login/Champ password'), 'blabla')

'Vérifier que le bouton connexion devient cliquable'
WebUI.verifyElementClickable(findTestObject('Page Login/Bouton connexion'))

'Vérifier que le type de compte apparait sur le bouton connexion'
WebUI.verifyElementAttributeValue(findTestObject('Page Login/Bouton connexion'), 'value', 'Connexion : ' + typeCompte, 3)

'Cliquer sur le bouton connexion'
WebUI.click(findTestObject('Page Login/Bouton connexion'))

'Vérifier l\'apparition d\'un message d\'erreur'
WebUI.waitForElementPresent(findTestObject('Page Login/Message err login'), 3)

'Vérifier le text sur le message d\'erreur'
WebUI.verifyElementText(findTestObject('Page Login/Message err login'), 'Utilisateur, mot de passe ou groupe incorrect')

'Vérifier que tous les champs sont vidés\r\n'
WebUI.verifyElementAttributeValue(findTestObject('Page Login/Champ user'), 'value', '', 3)

WebUI.verifyElementAttributeValue(findTestObject('Page Login/Champ password'), 'value', '', 3)

WebUI.verifyElementAttributeValue(findTestObject('Page Login/Champ groupe'), 'value', '', 3)

'Vérifier que le text du bouton devient connexion'
WebUI.verifyElementAttributeValue(findTestObject('Page Login/Bouton connexion'), 'value', 'Connexion', 3)

'Vérifier que le bouton connexion n\'est pas cliquable'
WebUI.verifyElementNotClickable(findTestObject('Page Login/Bouton connexion'))

'Saisir le bon login username et password'
WebUI.sendKeys(findTestObject('Page Login/Champ user'), GlobalVariable.loginUsername)

WebUI.sendKeys(findTestObject('Page Login/Champ password'), GlobalVariable.loginPassword)

'Vérifier que le bouton connexion devient cliquable'
WebUI.verifyElementClickable(findTestObject('Page Login/Bouton connexion'))

'Vérifier que le type de compte apparait sur le bouton connexion'
WebUI.verifyElementAttributeValue(findTestObject('Page Login/Bouton connexion'), 'value', 'Connexion : ' + typeCompte, 3)

'Cliquer sur le bouton connexion'
WebUI.click(findTestObject('Page Login/Bouton connexion'))

'Vérifier que mon compte et le type de compte apparait sur le header'
WebUI.waitForElementPresent(findTestObject('Header/Username header'), 10)

'Cliquer sur le username dans le header'
WebUI.click(findTestObject('Header/Username header'))

'Vérifier que le lien déconnexion apparait'
WebUI.waitForElementVisible(findTestObject('Page d accueil/Lien deconnexion'), 3)

WebUI.verifyElementText(findTestObject('Page d accueil/Lien deconnexion'), 'Déconnexion')

'Cliquer sur le lien déconnexion'
WebUI.click(findTestObject('Page d accueil/Lien deconnexion'))

WebUI.waitForElementPresent(findTestObject('Page Login/Champ user'), 5)

'Vérifier l apparition de la page avec les éléments suivants:\r\nChamp de saisi Utilisateur\r\nChamp mot de passe\r\nListe déroulante Groupe\r\nBouton de connexion'
WebUI.verifyElementPresent(findTestObject('Page Login/Champ user'), 3)

WebUI.verifyElementPresent(findTestObject('Page Login/Champ password'), 3)

WebUI.verifyElementText(findTestObject('Page Login/Titre S3'), 'S3')

WebUI.verifyElementText(findTestObject('Page Login/Label user'), 'Utilisateur')

WebUI.verifyElementText(findTestObject('Page Login/Label password'), 'Mot de passe')

WebUI.verifyElementText(findTestObject('Page Login/Label groupe'), 'Groupe')

WebUI.verifyElementAttributeValue(findTestObject('Page Login/Bouton connexion'), 'value', 'Connexion', 3)

'Vérifier que le bouton connexion n\'est pas cliquable'
WebUI.verifyElementNotClickable(findTestObject('Page Login/Bouton connexion'))

'Je renseigne l\'url de la Home page puis valide'
WebUI.navigateToUrl(GlobalVariable.url + 'activity/index.xhtml')

WebUI.delay(1)

WebUI.waitForElementPresent(findTestObject('Page Login/Champ user'), 5)

'Vérifier que je suis rester sur la page login'
WebUI.verifyElementPresent(findTestObject('Page Login/Champ user'), 3)

