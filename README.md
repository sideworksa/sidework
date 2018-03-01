# sidework 
created by @marykathrynsyms

**follow along**    
* [project road map](https://github.com/sideworksa/sidework/projects/1)       
* [slack workspace](https://sidework-sa.slack.com/)
* [instagram](https://www.instagram.com/sideworksa/)        
* [twitter](https://twitter.com/sideworksa)       



#### Sidework Feature List
- [ ] Visitors
    - [ ] View all workers, businesses, job listings
    - [ ] View `about us` and `how it works` pages
    - [ ] Create worker or business account
    - [ ] Ability to login `/login`
    - [ ] Ability to logout `/logout`
- [ ] Users
    - [ ] View a list of users, i.e. all users in the database, with links to individual users `/users` (business dashboard)
    - [ ] Edit a user's information `/users/{id}/edit` (access control)
    - [ ] Create User login `/login`
    - [ ] Delete a user (access control)
    - [ ] Doublecheck all validation error messages
- [ ] Workers
    - [ ] Create a worker `/workers/create` (registration page)
        - rows are inserted into the `workers` table
    - [ ] View a worker `/worker/{id}` (worker profile page)
    - [ ] View a list of users, i.e. all users in the database, with links to individual users `/workers` (view on business dashboard)
    - [ ] Edit a user's information `/workers/{id}/edit` (access control)
        - information in the database is changed
    - [ ] Delete a user (access control)    
    - [ ] Doublecheck all validation error messages
- [ ] Business
    - [ ] Create a business `/businesses/create`
        - The business should be related to the logged in user when creating
        - just logged in users should be able to visit this page
        - rows are inserted into the `businesses` table
    - [ ] View an individual business `/businesses/{id}` (business profile page)
    - [ ] View all businesses `/businesses`
    - [ ] Edit businesses page `/businesses/{id}/edit` (access control)
        - information in the database is changed
    - [ ] Search businesses by title & description
    - [ ] View the listings a business has created on the individual business page
    - [ ] Delete a business (access control)
    - [ ] Doublecheck all validation error messages
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
    - [ ] Validation error messages
- [ ] Miscellaneous
    - [ ] Deploy Live Site
    - [ ] Create a seeder with positions -- just a seeder.sql file (~15mins)
    - [ ] Add more test/dummy data to the seeder (for both development and presentation)
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
    - [ ] Upload profile picture to worker and business account
    - [ ] Enable in-browser messaging and notification system `covers`
    - [ ] Workers can save favorite businesses
            - [ ] view and edit list on dashboard
    - [ ] Businesses can save favorite workers
            - [ ] view and edit list on dashboard
    - [ ] Businesses can post automated job listings at future date
    - [ ] Business can upload menus pdf & floor chart documents to profile
    - [ ] Calendar API to view upcoming shift coverage
            - [ ] Worker views upcoming shifts, hover over for quick info regarding shift and business contact
                - [ ] ability to add their non-available days or half-day due to other jobs
            - [ ] Business views upcoming shifts, hover over for business contact
                - [ ] ability to add tentative events or * Reminders * when employee puts in 2 weeks notice, post new job listing  
    - [ ] Business profile has Google Maps API                      
    - [ ] Dashboard feature shows metrics of past shifts $ and total to date for Workers & Business
    
