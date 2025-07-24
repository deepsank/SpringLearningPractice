<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Search demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">

    <link href="<c:url value="/resources/css/style.css"/>" >
    <script src="<c:url value="/resources/js/script.js"/>"></script>
  </head>
  <body>

  <img alt="my image" src="<c:url value="/resources/image/Deepak_photo.jpg"/>">
    
<div class="container">
    <div class="card mx-auto mt-5 bg-primary" style="width: 50%;">
        <div class="card-body py-5">
        
            <h3 class="text-center text-white">SankDeep Search</h3>
            <form class="mt-3" action="search" >
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Enter your keyword" name="querybox">
                    
                </div>
                <div class="container text-center mt-3">
                <button class="btn btn-primary btn-outline-light" type="submit">Search</button>
                </div>
            </form>
        </div>
    </div>
</div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
  </body>
</html>