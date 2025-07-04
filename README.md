## Applicant Name : Ezz Eldin Alaa Eldin Omar Shalaby

## Description 
This is the ecommerce Java Application required for Fawri Quantum Internship Task

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

## Functionalities
###  Product Management
- Supports different product types:
  - Regular Products
  - Shippable Products
  - Expirable Products
  - Products that are both Shippable and Expirable

###  Customer Management
- Create customers with name, email, phone, and balance
- Validates email formats upon customer creation

###  Shopping Cart
- Add products to the cart with specified quantities
- Automatically checks product stock availability during addition
- Calculates the total price of items in the cart
- Supports clearing the cart

###  Checkout Process
- Validates product availability and expiration dates before checkout
- Calculates:
  - Subtotal
  - Shipping fees based on predefined delivery areas
  - Total amount
- Checks if the customer has sufficient balance before proceeding
- Deducts product quantities and customer balance after successful checkout
- Generates a detailed checkout receipt

### Shipping Service
- Ships shippable products and displays a shipment notice with item names and weights
- Calculates the total package weight

###  Order Management
- Creates and stores customer orders after successful checkout
- Tracks order status:
  - Pending
  - Processing
  - Shipped
  - Delivered
  - Cancelled

###  Test Cases in Main App
- Demonstrates various scenarios including:
  - Normal checkout with mixed products
  - Expired product prevention
  - Insufficient balance handling
  - Invalid shipping area handling
  - Stock depletion handling

