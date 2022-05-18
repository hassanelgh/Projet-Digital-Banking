# Backend Degital Banking 

### diagramme de classe :
![img.png](images/img.png)


### création des entities :
package :(`src/main/java/com/digitalbancking/digitalbancking/entities`)
    
    info :
    - dans l'héritage on utilise la stratégie : Single table

1. BankAccount : 

![img1.png](images/img_1.png)

2. SavingAccount :

![img2.png](images/img_2.png)

3. CurrentAccount :

![img3.png](images/img_3.png)


4. AccountOperation :

![img4.png](images/img_4.png)

5. Customer :

![img5.png](images/img_5.png)

### création des enums :
package :(`src/main/java/com/digitalbancking/digitalbancking/enums`)

1. AccountStatus :

![img6.png](images/img_6.png)


2. OperationType :

![img7.png](images/img_7.png)



### création des repositories :
package :(`src/main/java/com/digitalbancking/digitalbancking/repositories`)

1. AccountOperationRepository :

![img8.png](images/img_8.png)


2. BankAccountRepository :

![img9.png](images/img_9.png)


3. CustomerRepository :

![img10.png](images/img_10.png)


#### tester les repositories:
- modifier le fichier : application.properties :
![img11.png](images/img_11.png)

- ajouter des données (fonction : commandLineRunnerForTestRepositories): (`src/main/java/com/digitalbancking/digitalbancking/DigitalBanckingApplication.java`)
  - customers:
    ![img12.png](images/img_12.png)
    ![img15.png](images/img_15.png)

  - accounts :
    ![img13.png](images/img_13.png)
    ![img16.png](images/img_16.png)

  - operations :
    ![img14.png](images/img_14.png)
    ![img17.png](images/img_17.png)



### création des classes DTO :
package :(`src/main/java/com/digitalbancking/digitalbancking/dtos`)

  - AccountHistoryDTO :

    ![img37.png](images/img_37.png)

  - AccountOperationDTO :

    ![img38.png](images/img_38.png)

  - BankAccountDTO :

    ![img39.png](images/img_39.png)

  - CurrentAccountDTO :

    ![img40.png](images/img_40.png)

  - CustomerDTO :

    ![img41.png](images/img_41.png)

  - RequestAccountHistory :

    ![img42.png](images/img_42.png)

  - RequestCreditDTO :

    ![img43.png](images/img_43.png)

  - RequestCustomerDTO :

    ![img44.png](images/img_44.png)

  - RequestDebitDTO :

    ![img45.png](images/img_45.png)

  - RequestSaveCurrentAccountDTO :

    ![img46.png](images/img_46.png)

  - RequestSaveSavingAccountDTO :

    ![img47.png](images/img_47.png)

  - RequestTransferDTO :

    ![img48.png](images/img_48.png)

  - SavingAccountDTO :

    ![img49.png](images/img_49.png)



### création des mappers :

1. création de  l'interface BankAccountMapper et leur implementation (`src/main/java/com/digitalbancking/digitalbancking/mappers/BankAccountMapperImpl.java`)

   ![img50.png](images/img_50.png)

  - fonction fromCustomer :

  ![img51.png](images/img_51.png)
   
  - fonction fromCustomerDTO :

    ![img52.png](images/img_52.png)


   - fonction fromRequestCustomerDTO :

     ![img53.png](images/img_53.png)

   - fonction fromCurrentAccount :

     ![img54.png](images/img_54.png)

   - fonction fromCurrentAccountDTO :

     ![img55.png](images/img_55.png)

   - fonction fromSavingAccount :

     ![img56.png](images/img_56.png)

   - fonction fromSavingAccountDTO :

     ![img57.png](images/img_57.png)

   - fonction fromAccountOperation :

     ![img58.png](images/img_58.png)

   - fonction fromAccountOperationDTO :

     ![img59.png](images/img_59.png)

   - fonction fromRequestCurrentAccountDTOAndCustomer :

     ![img60.png](images/img_60.png)

   - fonction fromRequestSavingAccountDTOAndCustomer :

     ![img61.png](images/img_61.png)

   - fonction fromDebitDTOAndBankAccount :

     ![img62.png](images/img_62.png)

   - fonction fromCreditDTOAndBankAccount :

     ![img63.png](images/img_63.png)

   - fonction toAccountHistoryDTO :

     ![img64.png](images/img_64.png)


### création des Services :
package :(`src/main/java/com/digitalbancking/digitalbancking/services`)

1. création de l'interface bankAccountService et leur implementation (`src/main/java/com/digitalbancking/digitalbancking/services/BankAccountServiceImpl.java`) :

    ![img18.png](images/img_18.png)
    
  - fonction saveCustomer :
  
    ![img19.png](images/img_19.png)

  - fonction updateCustomer :

    ![img32.png](images/img_32.png)

  - fonction deleteCustomer :

    ![img33.png](images/img_33.png)

  - fonction getCustomer :
    
    ![img34.png](images/img_34.png)

  - fonction saveCurrentBankAccount :

    ![img20.png](images/img_20.png)

  - fonction saveSavingBankAccount :

    ![img21.png](images/img_21.png)

  - fonction listCustomers :

    ![img22.png](images/img_22.png)

  - fonction listBankAccount :

    ![img23.png](images/img_23.png)

  - fonction getBankAccount :

    ![img24.png](images/img_24.png)

  - fonction debit :

    ![img25.png](images/img_25.png)

  - fonction credit :

    ![img26.png](images/img_26.png)

  - fonction transfer :

    ![img27.png](images/img_27.png)

  - fonction accountOperations :

    ![img35.png](images/img_35.png)

  - fonction accountHistory :
    
    ![img36.png](images/img_36.png)


### création des Exceptions :
package :(`src/main/java/com/digitalbancking/digitalbancking/services`)

  - BalanceNotSufficientException

    ![img28.png](images/img_28.png)

  - BankAccountNotFoundException

    ![img29.png](images/img_29.png)

  - CustomerNotFoundException

    ![img30.png](images/img_30.png)



### tester les Services :

- ajouter des données (fonction : commandLineRunnerForTestServices) : (`src/main/java/com/digitalbancking/digitalbancking/DigitalBanckingApplication.java`)

  ![img31.png](images/img_31.png)


### création des RestController:

  - BankAccountRestController :

    ![img65.png](images/img_65.png)
    ![img66.png](images/img_66.png)

  - CustomerRestController :

    ![img67.png](images/img_67.png)

#### test  par l'utilisation de `swagger ui`:

 - tout les clients :

   ![img68.png](images/img_68.png)

 - ajouter un client :

   ![img69.png](images/img_69.png)

 - consulter un client :

   ![img70.png](images/img_70.png)

 - modifier un client :

   ![img71.png](images/img_71.png)

 - delete un client :

   ![img72.png](images/img_72.png)

 - consulter tous les comptes :

   ![img73.png](images/img_73.png)

 - consulter un compte :

   ![img74.png](images/img_74.png)

 - tout les opérations d'un compte :

   ![img75.png](images/img_75.png)

 - historique de compte avec la pagination :

   ![img76.png](images/img_76.png)

 - création de compte Saving :

   ![img77.png](images/img_77.png)

 - création de compte Current :

   ![img78.png](images/img_78.png)

 - effectuer un crédit :

   ![img79.png](images/img_79.png)

 - effectuer un débit :

   ![img80.png](images/img_80.png)

 - effectuer un virement :

   ![img81.png](images/img_81.png)














