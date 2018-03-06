# sidework 
created by @marykathrynsyms
<br>
<br>

### follow along   
* [project road map](https://github.com/sideworksa/sidework/projects/1)       
* [slack workspace](https://sidework-sa.slack.com/)
* [instagram](https://www.instagram.com/sideworksa/)        
* [twitter](https://twitter.com/sideworksa)       

<br>

### Sidework Feature List
- [ ] Visitors
    - [X] View all workers, businesses, job listings
    - [ ] View `about us` and `how it works` pages
    - [X] Create worker or business account
    - [X] Ability to login `/login`
- [ ] Users
    - [X] View a list of users, i.e. all users in the database, with links to individual users `/users` (business dashboard)
    - [ ] Edit a user's information `/users/{id}/edit` (access control)
    - [X] Create User login `/login`
    - [ ] Delete a user (access control)
    - [ ] Double check all validation error messages
    - [X] Ability to logout `/logout`
- [ ] Workers
    - [X] Create a worker `/workers/create` (registration page)
        - rows are inserted into the `workers` table
    - [X] View a worker `/worker/{id}` (worker profile page)
    - [X] View a list of users, i.e. all users in the database, with links to individual users `/workers` (view on business dashboard)
    - [ ] Edit a user's information `/workers/{id}/edit` (access control)
        - information in the database is changed
    - [ ] Delete a user (access control)    
    - [ ] Double check all validation error messages
- [ ] Businesses
    - [X] Create a business `/businesses/create`
        - The business should be related to the logged in user when creating
        - just logged in users should be able to visit this page
        - rows are inserted into the `businesses` table
    - [X] View an individual business `/businesses/{id}` (business profile page)
    - [X] View all businesses `/businesses`
    - [X] Edit businesses page `/businesses/{id}/edit` (access control)
        - information in the database is changed
    - [X] Search businesses by title & description
    - [ ] View the listings a business has created on the individual business page
    - [ ] Delete a business (access control)
    - [ ] Double check all validation error messages
- [ ] Job Listings
    - [ ] Create a listing with just details `/listings/create`
        - When a listing is created, the business_id of the logged in user
          should be attached to the listing
        - only users that have created a business should be able to access
          this page
        - rows are inserted into the `listings` table
    - [ ] View individual listing show page `/listings/{id}`
    - [ ] View all listings on index page `/listings` (view on worker dashboard)
    - [ ] Edit or remove a listing `/listings/{id}/edit` (the position was filed)
        - rows in the database are updated
    - [ ] Double check all validation error messages
- [ ] Miscellaneous
    - [ ] Deploy Live Site
    - [ ] Create a seeder with positions -- just a seeder.sql file (~15mins)
    - [ ] Add more test/dummy data to the seeder (for both development and demo)
    - [ ] Landing page that isn't hello world
    - [ ] About us page 
        - [ ] finalize content
        - [ ] email prompt, set up domain account
        - [ ] social media accounts
    - [ ] How it Works page
        - [ ] finalize content
        - [ ] quotes & bullet points hyperlinks to articles
        - [ ] feature businesses & workers
        - [ ] social media accounts 
    - [ ] Include the navbar on all the pages
    - [ ] Include sidebar verified with authentication on all pages when logged in 
- [ ] Ideal Features        
    - [ ] Positions     
        - [ ] Workers       
            - [ ] Attach a position_id to a worker when they are created          
            - [ ] Search workers by position        
            - [ ] Search (filter) workers by availability, employment status        
            - [ ] Add positions and availability/schedule to worker registration        
        - [ ] Job Listings      
            - [ ] a position_id is attached to a listing when it is created     
            - [ ] Search listings by position       
            - [ ] Add more details and search (filter) by these details     
            - [ ] Mark job listing as "covered"     
            - [ ] view in separate list     
    - [ ] Worker Account            
        - [ ] Upload profile picture        
        - [ ] Workers can save favorite businesses      
            - [ ] view and edit list on dashboard       
        - [ ] Calendar API to view upcoming shift coverage      
    - [ ] Business Account      
        - [ ] Upload profile picture        
        - [ ] Business can upload menus pdf & floor chart documents to profile      
        - [ ] Business profile has Google Maps API      
        - [ ] Businesses can save favorite workers      
            - [ ] view and edit list on dashboard       
        - [ ] Calendar API to view upcoming shift coverage      
        - [ ] Business views upcoming shifts, hover over for worker contact info & photo        
            - [ ] ability to add tentative events or * *Reminders* * when employee puts in 2 weeks notice, post new job listing     
            - [ ] Businesses can post automated job listings at future date          
    - [ ] Covers Feature 
        - [ ] enable in browser, real-time messaging and notification system     
    - [ ] Rating Feature        
        - [ ] Businesses rate workers after shift     
        - [ ] Worker rates experience & work environment                          
    - [ ] Availability Feature       
        - [ ] Carousel of available workers       
        - [ ] Featured order determined by top Sidework rated workers & rating                     
        - [ ] Worker text area form input for small blurb on current availability         
    - [ ] Shifts Feature 
        - [ ] shows metrics of past shifts $
        - [ ] total revenue to date for Workers & Business            
    
