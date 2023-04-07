# UserAPI-WebService
## was developed by using  Spring Security,JWT,Spring Data JPA,H2 in-memory DB (moreover MySQL),Spring Validation,Swagger,Lombok,Maven
#### There are 4 end-points.["/register","/login" is allowed to all users. Sends token. ]["/userlist" is allowed just users with role "ADMIN".]["/user/me" is allowed users with role USER or ADMIN, and response currently user information(taking from jwt token sent with authorization header)]

#### Wants 4 parameter : in order of  "name,email, password and role" for register requests. 2 parameter for login : email,password  
#### Email is must be valid and password between 8-16 character.

<img height="500" width="800"  src="https://user-images.githubusercontent.com/121498198/229936941-b88c9b76-28a9-49e5-ad5e-f9bc29b52edb.PNG"/>
<img height="500" width="800"  src="https://user-images.githubusercontent.com/121498198/229936595-e78c89f7-06f9-4ede-8475-f272686b2f70.PNG"/>
<img height="500" width="800"  src="https://user-images.githubusercontent.com/121498198/229941318-b3881e22-9a75-44fc-9dd3-951b87c6d8e4.PNG"/>
<img height="500" width="800"  src="https://user-images.githubusercontent.com/121498198/229936811-23993a2c-f523-4bc7-b083-e58c94f9874f.PNG"/>
<img height="500" width="800"  src="https://user-images.githubusercontent.com/121498198/229936947-c99c85e9-48ac-4741-8771-6c28280d9456.PNG"/>
<img height="500" width="800"  src="https://user-images.githubusercontent.com/121498198/229936952-7aba4441-4ef8-4b2b-b40a-c0a0e050c766.PNG"/>
<img height="500" width="800"  src="https://user-images.githubusercontent.com/121498198/229936959-0b1e7601-08a3-4cb2-b970-651d4d7db59e.PNG"/>



