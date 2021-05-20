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
import java.text.SimpleDateFormat as SimpleDateFormat

String numeroASwape = "$numeroASwape"

String nouvelIccid = "$nouvelICCIDSwape"

WebUI.callTestCase(findTestCase('02-Création activié/08-Création activité SIM Swap via un service'), [('serviceASwape') : GlobalVariable.serviceActifSwap2], 
    FailureHandling.CONTINUE_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Page d accueil/Section Mes activités/Colonne numero'), 5)

String numeroActivite = WebUI.getText(findTestObject('Page d accueil/Section Mes activités/Colonne numero'))

'Cliquer sur le numéro de l\'activité que l\'on vient de créer'
WebUI.click(findTestObject('Page d accueil/Section Mes activités/Colonne numero'))

WebUI.waitForElementPresent(findTestObject('Page Activité/Page activité (commun)/Fil ariane Activité'), 5)

numeroActiviteFilAriane = WebUI.getText(findTestObject('Page Activité/Page activité (commun)/Fil ariane Activité'))

'Vérifier que le champ service est prérempli par le numéro de service'
WebUI.verifyMatch(numeroActiviteFilAriane.substring(9), numeroActivite, false)

'Vérifier que le champ ancien ICCID est prérempli par le numero ICCD du numéro actif'
WebUI.verifyElementAttributeValue(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ Service'), 
    'value', numeroASwape, 3)

WebUI.verifyElementAttributeValue(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ Ancien ICCID'), 
    'value', GlobalVariable.ancienICCD, 3)

ancienICCID = WebUI.getAttribute(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ Ancien ICCID'), 
    'value')

'Choisir non dans le champ sortie de SIM'
WebUI.click(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Champ Sortie de SIM'))

WebUI.delay(0.500)

WebUI.click(findTestObject('Page Activité/Page activité (commun)/Formulaire Choix de Numéro/Option Oui Sortie de SIM'))

'Saisir le nouvel ICCID'
WebUI.sendKeys(findTestObject('Page Activité/Page activité (commun)/Formulaire Récupération de Numéro/Champ Nouvel ICCID'), 
    nouvelIccid)

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

'Cliquer sur le bouton valider'
WebUI.click(findTestObject('Page Activité/Page activité (commun)/Bouton Valider'))

'Vérifier l\'apparition du popin de confirmation avec les éléments suivants:\r\n- titre: Confirmation\r\n- message: Etes-vous sûr de vouloir clôturer cette activité?\r\n- Bouton oui et non\r\n'
WebUI.waitForElementVisible(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Titre popin'), 
    3)

WebUI.verifyElementText(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Titre popin'), 'Confirmation')

WebUI.verifyElementText(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Message de confirmation'), 
    'Etes-vous sûr de vouloir clôturer cette activité?')

WebUI.verifyElementPresent(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Bouton Non'), 3)

WebUI.verifyElementPresent(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Bouton Oui'), 3)

'Cliquer sur le bouton Oui du popin'
WebUI.click(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Bouton Oui'))

WebUI.waitForElementPresent(findTestObject('Page Activité/Page activité (commun)/Message d erreur orange'), 5)

WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Message d erreur orange'), 'Aucune pièce jointe n’a été déposée pour cette activité')

'Ajouter une pièce jointe'
WebUI.callTestCase(findTestCase('02-Création activié/00-Called Tests Case/Ajout pièce jointe'), [:], FailureHandling.CONTINUE_ON_FAILURE)

WebUI.waitForElementClickable(findTestObject('Page Activité/Page activité (commun)/Bouton Valider'), 5)

'Cliquer sur le bouton valider'
WebUI.click(findTestObject('Page Activité/Page activité (commun)/Bouton Valider'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.delay(0.500)

'Vérifier l\'apparition du popin de confirmation avec les éléments suivants:\r\n- titre: Confirmation\r\n- message: Etes-vous sûr de vouloir clôturer cette activité?\r\n- Bouton oui et non\r\n'
WebUI.waitForElementVisible(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Titre popin'), 
    3)

'Cliquer sur le bouton Oui du popin'
WebUI.click(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Bouton Oui'))

WebUI.waitForElementPresent(findTestObject('Page Commande/Bouton Payer'), 20, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('Page Commande/Montant à payer S3'), 20)

montantAPayer = WebUI.getText(findTestObject('Page Commande/Montant à payer S3'))

montantAPayer = montantAPayer.replace(',00', '' //suprimé le décimal
    )

println(montantAPayer)

'Cliquer sur le bouton Payer'
WebUI.click(findTestObject('Page Commande/Bouton Payer'), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Titre popin'), 
    5)

'Vérifier la conformité du popin'
WebUI.verifyElementText(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Titre popin'), 'Confirmation')

WebUI.verifyElementText(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Message de confirmation'), 
    'Etes-vous sûr de vouloir procéder à l\'encaissement ?')

'Cliquer sur le bouton Non'
WebUI.click(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Bouton Non'))

WebUI.delay(0.500)

'Cliquer à nouveau sur le bouton valider'
WebUI.click(findTestObject('Page Commande/Bouton Payer'))

'Vérifier l\'apparition du popin de confirmation avec les éléments suivants:\r\n- titre: Confirmation\r\n- message: Etes-vous sûr de vouloir clôturer cette activité?\r\n- Bouton oui et non\r\n'
WebUI.waitForElementVisible(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Titre popin'), 
    3)

'Vérifier l\'apparition du popin de confirmation avec les éléments suivants:\r\n- titre: Confirmation\r\n- message: Etes-vous sûr de vouloir clôturer cette activité?\r\n- Bouton oui et non\r\n'
WebUI.verifyElementText(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Message de confirmation'), 
    'Etes-vous sûr de vouloir procéder à l\'encaissement ?')

'Cliquer sur le bouton Oui du popin'
WebUI.click(findTestObject('Page Activité/Popin confirmation activite, commande (commun)/Bouton Oui'))

WebUI.delay(0.500)

//WebUI.switchToWindowUrl(GlobalVariable.urlPos + 'activity/encaissement-manage/paiement-article')
WebUI.switchToWindowIndex(2)

'Vérifier que la désignation Reservation numéro Regular est affiché dans pos'
WebUI.verifyElementPresent(findTestObject('POS/Page de vente/Designation'), 10)

WebUI.verifyElementText(findTestObject('POS/Page de vente/Designation'), (('CARTE SIM (' + nouvelIccid) + ')') + '\nS3')

prixUnitaire = WebUI.getText(findTestObject('POS/Page de vente/Montant POS'))

'Vérifier que le montant dans S3 est similaire a celui dans POS'
WebUI.verifyMatch(prixUnitaire, montantAPayer, false)

'Cliquer sur le bouton Payer'
WebUI.click(findTestObject('POS/Page de vente/Bouton Payer'))

WebUI.waitForElementPresent(findTestObject('POS/Page encaissement/Montant a encaisser'), 5)

montant = WebUI.getText(findTestObject('POS/Page encaissement/Montant a encaisser'))

montantAEncaisser = montant.substring(montant.lastIndexOf(':') + 2, montant.lastIndexOf('MGA') - 1)

//montantAEncaisser = montantAEncaisser.replaceAll('\\s', '') //supprimer les espaces
'Saisir le montant à encaisser dans POS'
WebUI.sendKeys(findTestObject('POS/Page encaissement/Champ montant'), montantAEncaisser)

WebUI.verifyElementClickable(findTestObject('POS/Page encaissement/Bouton Ajouter Paiement'))

'Cliquer sur le bouton Ajouter paiement'
WebUI.click(findTestObject('POS/Page encaissement/Bouton Ajouter Paiement'))

WebUI.verifyElementClickable(findTestObject('POS/Page encaissement/Bouton Encaisser'))

WebUI.click(findTestObject('POS/Page encaissement/Bouton Encaisser'))

//Récupération de la date actuel
def date = new Date()

def sdf = new SimpleDateFormat('dd/MM/yy HH:mm')

String dateHeureTraitement = sdf.format(date)

WebUI.delay(0.500)

'Cliquer sur le bouton Terminer du popin '
WebUI.waitForElementPresent(findTestObject('POS/Page finalisation de vente/Bouton Terminer'), 30)

WebUI.click(findTestObject('POS/Page finalisation de vente/Bouton Terminer'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('POS/Popin de Notification/Bouton radio Particulier'), 5)

'Choisir l\'option Particulier du popin'
WebUI.click(findTestObject('POS/Popin de Notification/Bouton radio Particulier'), FailureHandling.CONTINUE_ON_FAILURE)

'Cliquer sur le bouton Terminer du popin'
WebUI.waitForElementClickable(findTestObject('POS/Popin de Notification/Bouton Terminer'), 3)

WebUI.click(findTestObject('POS/Popin de Notification/Bouton Terminer'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('POS/Popin de Notification/Bouton Non'), 5)

'Cliquer sur le bouton Non'
WebUI.click(findTestObject('POS/Popin de Notification/Bouton Non'), FailureHandling.CONTINUE_ON_FAILURE)

'Retourner sur la page d\'activité de S3'
WebUI.navigateToUrl(GlobalVariable.url + 'activity/index.xhtml')

WebUI.delay(0.500)

'Vérifier que l\'on est redirigé vers la page d\'accueil'
WebUI.waitForElementPresent(findTestObject('Page d accueil/Section Mes activités/Colonne Activité'), 10)

'Vérifier l\'apparition de l\'activité qui vient d\'être crée '
WebUI.verifyElementText(findTestObject('Page d accueil/Section Mes activités/Colonne Activité'), 'Sim Swap')

'Vérifier que le statut est fermé'
WebUI.verifyElementText(findTestObject('Page d accueil/Section Mes activités/Colonne Statut'), 'Fermé')

String dateCreationActivite = WebUI.getText(findTestObject('Page d accueil/Section Mes activités/Colonne Date'))

dateCreationActivite = dateCreationActivite.substring(0, 14)

'Vérifier que la date du traitement est conforme'
WebUI.verifyMatch(dateCreationActivite, dateHeureTraitement, false)

WebUI.verifyElementText(findTestObject('Page d accueil/Section Mes activités/Colonne numero'), numeroActivite)

println('fin')

