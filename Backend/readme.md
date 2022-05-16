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





### création des Services :
package :(`src/main/java/com/digitalbancking/digitalbancking/services`)

1. création de l'interface bankAccountService et leur implementation (`src/main/java/com/digitalbancking/digitalbancking/services/BankAccountServiceImpl.java`) :

    ![img18.png](images/img_18.png)
    
  - fonction saveCustomer :

    ![img19.png](images/img_19.png)

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














