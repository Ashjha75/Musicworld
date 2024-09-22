# MusicStore

The Angular Components route from one to the other according to the following UI schema:

![...loading...](https://github.com/iambrunoromano/MusicStore/blob/main/musicstoreapp/schema/Components.drawio.png?raw=true)

The Angular componenet shown in the home page is the `Best` component. It will be decribed below betweeen the `/composedcomponents`.

# General

All the frontend has been developed in Angular. All the common-use application CSS styles are in the `/Styles` folder. The `/assets/img` folder contains sample images. The `/app` folder contains four folders: `/interfaces`, `/services`, `/composedcomponents`, `/simplecomponents`.

## Interfaces

The interfaces are used to convert REST APIs' responses in Typescript Objects. Therefore the interfaces mirror all the backend `com.musicstore.model` and `com.musicstore.pojos` classes. 

## Services

The services implement all the methods to call REST APIs, exactly mirroring the `com.musicstore.controller.api` classes' methods. Then services are instantiated in each component constructor where those are needed.  

## Simple Components

The simple components are components generated through Angular. In the future all the single components will provide base functionalities while complex-emerging functionalities willl be provided by composed components. At the current stage we have five simple components:
1. `Login` Component: handles the user login
2. `Register` Component: handles the user registration
3. `Order` Component: shows order general infos and ordered product infos after an order is done
4. `Product` Component: not used. Future use: modularize `AllProducts` component
5. `Category` Component: not used. Future use: modularize `AllCategories` component

## Composed Components

The composed components are complex components. The idea is to turn those into combination of simple components to take advantage of modularization. At the current stage we have five composed components:
1. `Best` Component: shows the best selling products and producers
2. `Producer` Component: shows all the products and categories for a chosen producer
3. `AllProducts` Component: shows all the products
4. `AllCategories` Component: shows all the product categories 
5. `Search` Component: - incomplete - will show the products that are the result of string search
