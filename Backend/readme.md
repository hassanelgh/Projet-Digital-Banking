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

- ajouter les données : (`src/main/java/com/digitalbancking/digitalbancking/DigitalBanckingApplication.java`)
  - customers:
    ![img12.png](images/img_12.png)
    ![img15.png](images/img_15.png)

  - accounts :
    ![img13.png](images/img_13.png)
    ![img16.png](images/img_16.png)

  - operations :
    ![img14.png](images/img_14.png)
    ![img17.png](images/img_17.png)

