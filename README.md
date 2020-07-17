Back-End-Trade-Point
======================
ABOUT
----------------------
This API allows creating new trade points, delete trade points, add a seller to chosen trade point and delete a seller from chosen trade point.

INSTRUCTION
----------------------

For all web requests required JWT token with assigned admin role.
For all mobile requests required JWT token with assigned customer role.
JWT token is the result of an auth request.
Read more here:
http://ecse005008ef.epam.com:8080/swagger/auth-mobile/swagger-ui.html
http://ecse005008ef.epam.com:8080/swagger/auth-web/swagger-ui.html
You must pass it in the header of a request

Swagger: http://ecse005008ef.epam.com:8080/swagger/trade-point/swagger-ui.html#

REQUIREMENT
------------
Java 11, Maven, Spring - boot, PostgreSQL, Docker
