<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">XPROD</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item"><a class="nav-link active"
                        href="index.jsp">Home <span class="sr-only">(current)</span></a></li>

                    <li class="nav-item"><a class="nav-link" href="card.jsp">Cart<span class="badge badge-danger"> ${cart_list.size()}</span></a></li>
                     <%if ("auth"!=null){ %>
                    <li class="nav-item"><a class="nav-link"
                        href="orders.jsp">Order</a></li>

                    <li class="nav-item"><a class="nav-link"
                        href="log-out">Deconnexion</a></li>
                    <%}else{ %>
                    <li class="nav-item"><a class="nav-link"
                        href="login.jsp">login</a></li>
                     <%} %>
                </ul>
                <form class="form-inline my-2 my-lg-0">
                    <input class="form-control mr-sm-2" type="search"
                        placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </form>
            </div>
        </div>
    </div>
</nav>