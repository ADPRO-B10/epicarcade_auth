# EpicArcade Authentication

### Endpoints: 
- http://34.128.91.126/api/auth/login
- http://34.128.91.126/api/auth/register

### TODO:
- [x] fix connection to postgreSQL (user password error)
- [ ] make and run more tests
- [ ] deploy to gcp
- [x] test endpoints with postman

### Request Body
`POST /auth/register/`
#### BUYER
```json
{
"username": "usernamefuad",
"email": "fuadracing@gmail.com",
"password": "fuadpassword",
"role": "2"
}
```
#### SELLER
```json
{
"username": "usernamerusdi",
"email": "rusdiracing@gmail.com",
"password": "rusdipassword",
"role": "3"
}
```

`POST /auth/login/`
#### BUYER
```json
{
  "email": "fuadracing@gmail.com",
  "password": "fuadpassword"
}
```
#### SELLER
```json
{
  "email": "rusdiracing@gmail.com",
  "password": "rusdipassword"
}
```

### Response Body
`POST /auth/register/`
```json
"User registered successfully"
```

`POST /auth/login/`
```json
{
    "userID": 2,
    "username": "usernamefuad",
    "email": "fuadracing@gmail.com",
    "role": "BUYER",
    "profilePictureUrl": null,
    "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmdWFkcmFjaW5nQGdtYWlsLmNvbSIsImlhdCI6MTcxNjYzODAxNCwiZXhwIjoxNzE2NjM4MDg0fQ.rMGRvZm1UYHbo7bzsUJUDBzYK8wEVwyCYnTDp94pD4kJn3-_Dr9xUv3ZWTeb7i1-5cqOXuxtCSezv4XAhniJbQ",
    "tokenType": "Bearer "
}
```
```json
{
    "userID": 1,
    "username": "usernamerusdi",
    "email": "rusdiracing@gmail.com",
    "role": "SELLER",
    "profilePictureUrl": null,
    "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJydXNkaXJhY2luZ0BnbWFpbC5jb20iLCJpYXQiOjE3MTY2MzgwMDYsImV4cCI6MTcxNjYzODA3Nn0.QnRhDJJgh914qUc7Ffk_AnogtA292EMyDo12j0o4YdRuySTfFLc4cijJPDtuPJ_qYI8H48saE3MsMduQbaSAKw",
    "tokenType": "Bearer "
}
```


---

#### References:
- https://github.com/hello-iftekhar/springJwt
- https://www.youtube.com/playlist?list=PL82C6-O4XrHe3sDCodw31GjXbwRdCyyuY
