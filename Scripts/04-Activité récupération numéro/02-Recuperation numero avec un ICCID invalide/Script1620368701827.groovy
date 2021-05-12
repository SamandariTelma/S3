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
import java.awt.event.KeyEvent as KeyEvent
import org.openqa.selenium.Keys as Keys

'Prérequis: Création d\'un activité récupération numéro'
WebUI.callTestCase(findTestCase('02-Création activié/07-Création activité récupération numero via un service'), [:], FailureHandling.CONTINUE_ON_FAILURE)

//WebUI.callTestCase(findTestCase('00-Called Test Case/Connexion à S3'), [:], FailureHandling.CONTINUE_ON_FAILURE)
WebUI.waitForElementPresent(findTestObject('Page d accueil/Section Mes activités/Colonne numero'), 5)

String numeroActivite = WebUI.getText(findTestObject('Page d accueil/Section Mes activités/Colonne numero'))

'Cliquer sur le numéro de l\'activité que l\'on vient de créer'
WebUI.click(findTestObject('Page d accueil/Section Mes activités/Colonne numero'))

WebUI.waitForElementPresent(findTestObject('Page Activité/Page activité (commun)/Fil ariane Activité'), 5)

numeroActiviteFilAriane = WebUI.getText(findTestObject('Page Activité/Page activité (commun)/Fil ariane Activité'))

'Vérifier que le fil d\'ariane comporte le numéro de l\'activité que l\'on vient de séléctionner'
WebUI.verifyMatch(numeroActiviteFilAriane.substring(9), numeroActivite, false)

'Vérifier que le champ service est préremplie par le numéro de service résilié que l\'on vient de séléctionner'
WebUI.verifyElementAttributeValue(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ Service'), 
    'value', GlobalVariable.serviceResilie1, 3)

'Choisir non dans le champ sortie de SIM'
WebUI.click(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ Sortie de SIM'))

WebUI.delay(0.500)

WebUI.click(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Option Non Sortie de SIM'))

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

'Ajouter une pièce jointe'
WebUI.callTestCase(findTestCase('02-Création activié/00-Called Tests Case/Ajout pièce jointe'), [:], FailureHandling.CONTINUE_ON_FAILURE)

'Vérifier que le bouton valider reste non cliquable'
WebUI.verifyElementNotClickable(findTestObject('Page Activité/Page activité (commun)/Bouton Valider'))

'Saisir un ICCID au mauvais format puis valider'
WebUI.sendKeys(findTestObject('Page Activité/Page activité (commun)/Formulaire Récupération de Numéro/Champ Nouvel ICCID'), 
    '5')

WebUI.click(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ Commentaires'))

WebUI.delay(2)

WebUI.waitForElementVisible(findTestObject('Page Activité/Page activité (commun)/Message d erreur orange'), 5)

WebUI.waitForElementPresent(findTestObject('Page Activité/Page activité (commun)/Message d erreur orange'), 3)

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Message d erreur orange'), 'Veuillez vérifier la saisie du nouvel ICCID renseigné')

'Vérifier que le bouton valider reste non cliquable'
WebUI.verifyElementNotClickable(findTestObject('Page Activité/Page activité (commun)/Bouton Valider'))

WebUI.sendKeys(findTestObject('Page Activité/Page activité (commun)/Formulaire Récupération de Numéro/Champ Nouvel ICCID'), 
    Keys.chord(Keys.CONTROL, 'a'))

WebUI.sendKeys(findTestObject('Page Activité/Page activité (commun)/Formulaire Récupération de Numéro/Champ Nouvel ICCID'), 
    Keys.chord(Keys.BACK_SPACE))

'Saisir le même ICCID que le numéro de service'
WebUI.sendKeys(findTestObject('Page Activité/Page activité (commun)/Formulaire Récupération de Numéro/Champ Nouvel ICCID'), 
    GlobalVariable.ancienICCD)

WebUI.click(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ Commentaires'))

WebUI.delay(2)

WebUI.waitForElementVisible(findTestObject('Page Activité/Page activité (commun)/Message d erreur orange'), 5)

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Message d erreur orange'), 'L’identifiant ICCID renseigné est le même que celui de la sim actuelle')

'Vérifier que le bouton valider reste non cliquable'
WebUI.verifyElementNotClickable(findTestObject('Page Activité/Page activité (commun)/Bouton Valider'))

WebUI.sendKeys(findTestObject('Page Activité/Page activité (commun)/Formulaire Récupération de Numéro/Champ Nouvel ICCID'), 
    Keys.chord(Keys.CONTROL, 'a'))

WebUI.sendKeys(findTestObject('Page Activité/Page activité (commun)/Formulaire Récupération de Numéro/Champ Nouvel ICCID'), 
    Keys.chord(Keys.BACK_SPACE))

'Saisir un ICCID appartenant à un numéro postpaid'
WebUI.sendKeys(findTestObject('Page Activité/Page activité (commun)/Formulaire Récupération de Numéro/Champ Nouvel ICCID'), 
    GlobalVariable.iccidPostpaid)

WebUI.click(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ Commentaires'))

WebUI.delay(3)

WebUI.waitForElementVisible(findTestObject('Page Activité/Page activité (commun)/Message d erreur orange'), 5)

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Message d erreur orange'), 'Le numéro provisoire est un numéro POSTPAID')

'Vérifier que le bouton valider reste non cliquable'
WebUI.verifyElementNotClickable(findTestObject('Page Activité/Page activité (commun)/Bouton Valider'))

WebUI.sendKeys(findTestObject('Page Activité/Page activité (commun)/Formulaire Récupération de Numéro/Champ Nouvel ICCID'), 
    Keys.chord(Keys.CONTROL, 'a'))

WebUI.sendKeys(findTestObject('Page Activité/Page activité (commun)/Formulaire Récupération de Numéro/Champ Nouvel ICCID'), 
    Keys.chord(Keys.BACK_SPACE))

WebUI.click(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ Commentaires'))

WebUI.delay(3)

WebUI.waitForElementVisible(findTestObject('Page Activité/Page activité (commun)/Message d erreur orange'), 5)

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Message d erreur orange'), 'Veuillez saisir le nouvel ICCID')

'Vérifier que le bouton valider reste non cliquable'
WebUI.verifyElementNotClickable(findTestObject('Page Activité/Page activité (commun)/Bouton Valider'))

'Saisir un ICCID qui n\'existe pas'
WebUI.sendKeys(findTestObject('Page Activité/Page activité (commun)/Formulaire Récupération de Numéro/Champ Nouvel ICCID'), 
    '8926100000000942201')

WebUI.click(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ Commentaires'))

WebUI.delay(3)

WebUI.waitForElementVisible(findTestObject('Page Activité/Page activité (commun)/Message d erreur orange'), 5)

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Message d erreur orange'), 'The requested iccid does not exist')

'Vérifier que le bouton valider reste non cliquable'
WebUI.verifyElementNotClickable(findTestObject('Page Activité/Page activité (commun)/Bouton Valider'))

