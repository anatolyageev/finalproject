<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 4/4/2020
  Time: 17:57
  To change this template use File | Settings | File Templates.
--%>
<div class="container-fluid">
    <nav class="navbar navbar-expand-lg navbar-light bg-light rounded">
               <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample09"
                aria-controls="navbarsExample09" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarsExample09">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="controller?command=adminPageCommand"><fmt:message
                            key="admin.heading_admin"/></a> &nbsp;&nbsp;
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Link</a>
                </li>
                <li class="nav-item">

                    <a class="nav-link" href="controller?command=viewSettings"><fmt:message
                            key="header.menu_settings"/></a> &nbsp;

                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="http://example.com" id="dropdown09"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span
                            class="flag-icon flag-icon-us"> </span> ${sessionScope.currentLocale} </a>
                    <div class="dropdown-menu" aria-labelledby="dropdown09">
                        <a class="dropdown-item" href="changeLocale.jsp?locale=en"><fmt:message
                                key="local.local_button.name.en"/></a>
                        <a class="dropdown-item" href="changeLocale.jsp?locale=ru"><fmt:message
                                key="local.local_button.name.ru"/></a>
                    </div>
                </li>
            </ul>
        </div>
    </nav>
</div>

