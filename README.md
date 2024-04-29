##Bank Management Utility
# Group 1

# Downloading IntelliJ IDEA
- Go to this link: https://www.jetbrains.com/idea/download/?section=windows
- Download the best version of IntelliJ IDEA for your system.
- You should be able to get IntelliJ IDEA for free with a uah.edu email.

# Downloading MySQL
- Go to this link: https://www.mysql.com/products/community/
- Click “Download MySQL Community Edition.”
- Click “MySQL Installer for Windows.”
- Here, you will need to sign in or create an Oracle account.
- Follow installation steps. We chose the “Full” version.

# Setting Up MySQL
- Enter MySQL Workbench.
- Under MySQL Connections, either select Local Instance or create a new connection by clicking the + button. Using basic configurations is fine. Username: root. Password: kd(S(MavJCoLV1.
- Enter the connection by entering your password.
- Create a new schema by clicking on the cylinder with a + in the top left. “laravel_api” should be the name.
- Make sure the schema is in bold by double clicking on the name in the left bar.
- Open a new SQL tab by clicking on the page symbol with a + in the top left. 
- In the submission folder, open the SQL schema file. Copy and paste the entire file into the SQL tab in MySQL. Run the SQL tab by clicking on the lightning symbol at the top. Make sure you click on the lightning symbol without the cursor symbol.
- You can at the tables right clicking on the schema -> schema inspector -> tables.
- You can look at the content of the tables by making a new SQL tab and running the following command: select * from <table name>.

# Downloading Vue/Vuetify
- Go to this link to download NodeJS: https://nodejs.org/en#download. Follow the download instructions.
- Open IntelliJ IDEA and open a new project. Go to the submission folder and select the project folder titled “Senior-Design.” Ignore “Senior-Design-Spring” for now.
- Ensure you have the following plugins installed: Typescript, JavaScript Debugger, and Vue.js. You can check by going to Settings -> Plugins and looking under the installed tab. It may be easier to use the search bar.
- Open a terminal window located in the bottom left corner. Run the following commands:
  - npm install vue
  - npm install vuetify
  - npm install @mdi/js @jamescoyle/vue-icon
  - npm install pinia
  - npm install pinia-plugin-persistedstate
  - npm install

# Starting the Server
- In IntelliJ IDEA, open a new project in the file tab. Make sure to open it in a new window. Select the “Senior-Design-Spring” folder.
- In the files on the left, navigate through the following path: src\main\java\com\example\seniordesignspring\SeniorDesignSpringApplication.java
- Click on run to begin the server. Do this by clicking on the green play button in the top right. You can stop the server by clicking on the red square, but you need to have the server running while using the website.

# Getting to the Website
- Go back to the “Senior-Design” project.
- Open a terminal and run the following command: cd src/main/frontend/bank-frontend
- Now run the following command: npm run dev
- After a moment, it will produce a link. Clicking it will bring you to the homepage for the bank.

# Account Credentials
- Here is a list of login credentials for some users so you can review the website. They will be in the format of username, password.
- Customers
  - alice_jones, AliceJones123!!
  - jmatt, Jm4tt!@#
  - ophelia_g, Qwerty!23
  - bbrown, Barb23#@!
- Tellers
  - martin_smith, M5m!tH#@!
  - em_johnson, $nH0jM3@$
  - james_davis, J3s!21&1s3
- Administrator
  -bank_admin, 5sdJ!A32#%d
