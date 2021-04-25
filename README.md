# authentication-with-springboot
basic authen and oauth2

1. create user with username, password, role (/authentication/user [POST])

2. POST /authentication/oauth/token
      body username: username
           password: password
           grant_type: password
           
   to get access_token
   
3. test with route /test ,/test/basic-only, /test/oauth-superuser
