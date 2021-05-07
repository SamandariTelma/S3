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

/*'Prérequis: Création d\'un activité SIM Swap via un service actif'
WebUI.callTestCase(findTestCase('02-Création activié/08-Création activité SIM Swap via un service'), [:], FailureHandling.CONTINUE_ON_FAILURE)*/
'Prérequis: Création d\'un activité Sim Swap'
WebUI.callTestCase(findTestCase('02-Création activié/08-Création activité SIM Swap via un service'), [:], FailureHandling.CONTINUE_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Page d accueil/Section Mes activités/Colonne numero'), 5)

String numeroActivite = WebUI.getText(findTestObject('Page d accueil/Section Mes activités/Colonne numero'))

'Cliquer sur le numéro de l\'activité que l\'on vient de créer'
WebUI.click(findTestObject('Page d accueil/Section Mes activités/Colonne numero'))

WebUI.waitForElementPresent(findTestObject('Page Activité/Page activité (commun)/Fil ariane Activité'), 5)

numeroActiviteFilAriane = WebUI.getText(findTestObject('Page Activité/Page activité (commun)/Fil ariane Activité'))

'Vérifier que le fil d\'ariane comporte le numéro de l\'activité que l\'on vient de séléctionner'
WebUI.verifyMatch(numeroActiviteFilAriane.substring(9), numeroActivite, false)

'Vérifier que le champ ancien ICCID est prérempli par le numero ICCD du numéro actif'
WebUI.verifyElementAttributeValue(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ Service'), 
    'value', GlobalVariable.serviceActifSwap1, 3)

WebUI.verifyElementAttributeValue(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ Ancien ICCID'), 
    'value', GlobalVariable.ancienICCD, 3)

ancienICCID = WebUI.getAttribute(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ Ancien ICCID'), 
    'value')

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

'Cliquer sur le bouton valider sans renseingé auccun ICCID'
WebUI.click(findTestObject('Page Activité/Page activité (commun)/Bouton Valider'))

'Vérifier l\'apparition du popin de confirmation avec les éléments suivants:\r\n- titre: Confirmation\r\n- message: Etes-vous sûr de vouloir clôturer cette activité?\r\n- Bouton oui et non\r\n'
WebUI.waitForElementVisible(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Titre popin'), 
    3)

WebUI.verifyElementText(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Titre popin'), 'Confirmation')

WebUI.verifyElementText(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Message de confirmation'), 
    'Etes-vous sûr de vouloir clôturer cette activité?')

WebUI.verifyElementPresent(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Bouton Non'), 3)

WebUI.verifyElementPresent(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Bouton Oui'), 3)

'Cliquer sur le bouton Non du popin'
WebUI.click(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Bouton Non'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('Page Activité/Page activité (commun)/Bouton Valider'))

WebUI.click(findTestObject('Page Activité/Page activité (commun)/Bouton Valider'))

WebUI.waitForElementVisible(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Message de confirmation'), 
    3)

'Cliquer sur le bouton Oui du popin'
WebUI.click(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Bouton Oui'))

WebUI.waitForElementPresent(findTestObject('Page Activité/Page activité (commun)/Message d erreur orange'), 5)

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Message d erreur orange'), 'Veuillez saisir le nouvel ICCID')

'Saisir un ICCID au mauvais format puis valider'
WebUI.sendKeys(findTestObject('Page Activité/Page activité (commun)/Formulaire Récupération de Numéro/Champ Nouvel ICCID'), 
    '5')

WebUI.click(findTestObject('Page Activité/Page activité (commun)/Bouton Valider'))

WebUI.waitForElementVisible(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Titre popin'), 
    3)

WebUI.click(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Bouton Oui'))

WebUI.delay(0.500)

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Message d erreur orange'), 'Veuillez vérifier la saisie du nouvel ICCID renseigné')

WebUI.clearText(findTestObject('Page Activité/Page activité (commun)/Formulaire Récupération de Numéro/Champ Nouvel ICCID'), 
    FailureHandling.CONTINUE_ON_FAILURE)

'Saisir le même ICCID que le numéro de service'
WebUI.sendKeys(findTestObject('Page Activité/Page activité (commun)/Formulaire Récupération de Numéro/Champ Nouvel ICCID'), 
    ancienICCID)

WebUI.click(findTestObject('Page Activité/Page activité (commun)/Bouton Valider'))

WebUI.waitForElementVisible(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Titre popin'), 
    3)

WebUI.click(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Bouton Oui'))

WebUI.delay(0.500)

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Message d erreur orange'), 'L’identifiant ICCID renseigné est le même que celui de la sim actuelle')

WebUI.clearText(findTestObject('Page Activité/Page activité (commun)/Formulaire Récupération de Numéro/Champ Nouvel ICCID'), 
    FailureHandling.CONTINUE_ON_FAILURE)

'Saisir un ICCID déjà swapé auparavant'
WebUI.sendKeys(findTestObject('Page Activité/Page activité (commun)/Formulaire Récupération de Numéro/Champ Nouvel ICCID'), 
    GlobalVariable.iccidDejaSwape)

WebUI.click(findTestObject('Page Activité/Page activité (commun)/Bouton Valider'))

WebUI.waitForElementVisible(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Titre popin'), 
    3)

WebUI.click(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Bouton Oui'))

WebUI.delay(2)

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Message d erreur orange'), 'L’identifiant ICCID renseigné n’est pas disponible')

WebUI.clearText(findTestObject('Page Activité/Page activité (commun)/Formulaire Récupération de Numéro/Champ Nouvel ICCID'), 
    FailureHandling.CONTINUE_ON_FAILURE)

'Saisir un ICCID appartenant à un numéro postpaid'
WebUI.sendKeys(findTestObject('Page Activité/Page activité (commun)/Formulaire Récupération de Numéro/Champ Nouvel ICCID'), 
    GlobalVariable.iccidPostpaid)

WebUI.click(findTestObject('Page Activité/Page activité (commun)/Bouton Valider'))

WebUI.waitForElementVisible(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Titre popin'), 
    3)

WebUI.click(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Bouton Oui'))

WebUI.delay(2)

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Message d erreur orange'), 'Le numéro provisoire est un numéro POSTPAID')

