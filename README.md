# The Hotel Booking
This is one of the assignments of Java Web LAB, which is a subject at FPT University.

## Features
This app uses Filter as Main Controller and follow MVC2 model.

### Function 1: Login
* In order to booking, an authentication is required
* If the user has not authenticated, the system redirects to the registration page
* This function includes logout and welcome functions
* Using Google reCAPTCHA service

### Function 2:  Show all available room and search room
* The screen lists all hotel which has an available room in the system
* User can find the room based on hotel name or hotel area and check in date and check out date and 
amount of room
* Login is not required

### Function 3: Register new account
* A new user is allowed to register new account with required information such as email as ID, phone, name, 
address, create date
* Current date is set default to create date field
* The default status of new user is active
* Password must be encrypted using SHA-256 before store in database

### Function 4:  Book Room
* All users can use this function except admin role (login is required)
* Each user can book any available room in the list (not limit the amount room want to book)
* User can view the selected room in the cart. The screen must show the total amount of money of this cart
* User can remove the room from the cart. The confirm message will show before delete action
* User can update amount of each room in cart
* User clicks the Confirm button to store the booking to database (must store the buy date time). The 
warning message will show if the selected room is out of stock
* During booking, user can enter the discount code (if any). Each discount code has its expiry date

### Function 5: Show Booking history 

### Function 6: Confirm Booking using email

<!-- ### Function 8:  Feedback on the quality of room service
* (in progress) -->
## Contact me via
1. [GitHub](www.github.com/chloe-nguyen)
2. [Linkedin](www.linkedin.com/in/chloe-nguyen-1206)
3. Email: *chloenguyen1206@gmail.com*

### © 2020 by chloe-nguyen

