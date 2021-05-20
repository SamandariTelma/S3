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

'Se connecter à S3'
WebUI.callTestCase(findTestCase('00-Called Test Case/Connexion à S3'), [:], FailureHandling.CONTINUE_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Header/Menu client'), 10)

'Cliquer sur le menu Client'
WebUI.click(findTestObject('Header/Menu client'))

WebUI.waitForElementPresent(findTestObject('Page Client/Section mes comptes/Titre section mes comptes'), 5)

List<WebElement> colonneNumero = WebUiCommonHelper.findWebElements(findTestObject('Page Client/Section mes comptes/Colonne numéro'), 
    5)

'Rechercher le compte choix de numéro en cours'
for (def numero : colonneNumero) {
    if (numero.getText().equals(GlobalVariable.numeroCompte)) {
        numero.click()
    }
}

WebUI.waitForElementPresent(findTestObject('Page Compte/Fil ariane Compte'), 3)

'Redirection vers la page du compte séléctionné, je vois le fil d\'ariane avec le numéro du compte'
WebUI.verifyElementText(findTestObject('Page Compte/Fil ariane Compte'), 'Compte ' + GlobalVariable.numeroCompte)

def personneMorale = [:]

nom = WebUI.getText(findTestObject('Page Compte/Section Personne Morale/01-nom'))

personneMorale.put('nom', nom)

stat = WebUI.getText(findTestObject('Page Compte/Section Personne Morale/02-stat'))

personneMorale.put('stat', stat)

nif = WebUI.getText(findTestObject('Page Compte/Section Personne Morale/03-nif'))

personneMorale.put('nif', nif)

referenceBudget = WebUI.getText(findTestObject('Page Compte/Section Personne Morale/04-reference budgetaire'))

personneMorale.put('referenceBudget', referenceBudget)

mail = WebUI.getText(findTestObject('Page Compte/Section Personne Morale/05-mail'))

personneMorale.put('mail', mail)

tel = WebUI.getText(findTestObject('Page Compte/Section Personne Morale/06-tel'))

personneMorale.put('tel', tel)

rue = WebUI.getText(findTestObject('Page Compte/Section Personne Morale/07-rue'))

personneMorale.put('rue', rue)

adresse = WebUI.getText(findTestObject('Page Compte/Section Personne Morale/08-adresse'))

personneMorale.put('adresse', adresse)

province = WebUI.getText(findTestObject('Page Compte/Section Personne Morale/09-province'))

personneMorale.put('province', province)

region = WebUI.getText(findTestObject('Page Compte/Section Personne Morale/10-region'))

personneMorale.put('region', region)

district = WebUI.getText(findTestObject('Page Compte/Section Personne Morale/11-district'))

personneMorale.put('district', district)

commune = WebUI.getText(findTestObject('Page Compte/Section Personne Morale/12-commune'))

personneMorale.put('commune', commune)

fokotany = WebUI.getText(findTestObject('Page Compte/Section Personne Morale/13-fokotany'))

personneMorale.put('fokotany', fokotany)

codePostal = WebUI.getText(findTestObject('Page Compte/Section Personne Morale/14-code postal'))

personneMorale.put('codePostal', codePostal)

'Cliquer sur l\'onglet Activités'
WebUI.click(findTestObject('Page Compte/Onglet Activite/Onglet activité'))

WebUI.delay(0.500)

WebUI.waitForElementVisible(findTestObject('Page Compte/Onglet Activite/Colonne Activite'), 3)

List<WebElement> colonneNumeroActivite = WebUiCommonHelper.findWebElements(findTestObject('Page Compte/Onglet Activite/Colonne numero'), 
    5)

List<WebElement> colonneActivite = WebUiCommonHelper.findWebElements(findTestObject('Page Compte/Onglet Activite/Colonne Activite'), 
    5)

List<WebElement> colonneStatut = WebUiCommonHelper.findWebElements(findTestObject('Page Compte/Onglet Activite/Colonne Statut'), 
    5)

int resultSize = colonneNumeroActivite.size

boolean isfound = false

String numero

for (i = 0; (i < resultSize) && (isfound == false); i++) {
    def activite = colonneActivite.get(i)

    String nomActivite = activite.getText()

    def statut = colonneStatut.get(i)

    String nomStatut = statut.getText()

    def numeroActivite = colonneNumeroActivite.get(i)

    numero = numeroActivite.getText()

    if (nomActivite.equals('Sim Swap') && nomStatut.equals('En cours')) {
        activite.click()

        isfound = true
    }
}

WebUI.waitForElementPresent(findTestObject('Page Activité/Page activité (commun)/Fil ariane Activité'), 5)

'Vérifier que le numéro du fil d\'ariane correspond au numéro de l\'activité séléctionné'
WebUI.verifyElementText(findTestObject('Page Activité/Page activité (commun)/Fil ariane Activité'), 'Activité ' + numero)

'Vérifier la conformité de la section Activité'
WebUI.callTestCase(findTestCase('00-Called Test Case/Activite/Vérification de la setion Activité'), [:], FailureHandling.CONTINUE_ON_FAILURE)

'Vérifier la conformité de la section Personne morale'
WebUI.callTestCase(findTestCase('00-Called Test Case/Activite/Vérification de la section Personne Morale'), [('personneMorale') : personneMorale], 
    FailureHandling.CONTINUE_ON_FAILURE)

'Vérifier la conformité du formulaire'
WebUI.callTestCase(findTestCase('05-Activité SIM Swap/04-Vérification formulaire SIM Swap'), [:], FailureHandling.CONTINUE_ON_FAILURE)

'Vérifier la présence du bouton Nouveau avec les onglets suivants:\r\n- Commentaires\r\n- Pièces jointes\r\n- Historiques'
WebUI.verifyElementPresent(findTestObject('Page Activité/Page activité (commun)/Section Onglets/Bouton Nouveau commentaire'), 3)

WebUI.verifyElementPresent(findTestObject('Page Activité/Page activité (commun)/Section Onglets/Onglet Commentaire'), 3)

WebUI.verifyElementPresent(findTestObject('Page Activité/Page activité (commun)/Section Onglets/Onglet Piece jointe'), 3)

WebUI.verifyElementPresent(findTestObject('Page Activité/Page activité (commun)/Section Onglets/Onglet Historique'), 3)

