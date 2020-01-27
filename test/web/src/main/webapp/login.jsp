<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>CTFLY - Login</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
  <link rel="stylesheet" href="./resources/css/stylelogin.css">
</head>

<body>
  <div class="tile">
    <div class="tile-header">
      <h2
        style="color: white; opacity: .75; font-size: 4rem; display: flex; justify-content: center; align-items: center; height: 100%;">
      </h2>
    </div>

    <div class="tile-body">
      <form id="form" method="POST" action="${contextPath}/login">
        <div class="message">${message}</div>
        <label class="form-input">
          <i class="material-icons">person</i>
          <input type="text" autofocus="true" name="username" />
          <span class="label">Utente</span>
          <span class="underline"></span>
        </label>

        <label class="form-input">
          <i class="material-icons">lock</i>
          <input type="password" name="password" />
          <span class="label">Password</span>
          <div class="underline"></div>
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </label>
        <div class="error">${error}</div>
        <div class="submit-container clearfix" style="margin-top: 2rem;">
          <p onclick="document.forms[0].submit();" class="btn btn-irenic float-right" tabindex="0">
            ACCEDI
          </p>
          <h3 class="text-center"><a href="${contextPath}/registration">Crea un account</a></h3>
        </div>
    </div>
    </form>
  </div>
  </div>
</body>

</html>