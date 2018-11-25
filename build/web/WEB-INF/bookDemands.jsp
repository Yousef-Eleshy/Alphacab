<%-- 
    Document   : bookDriver
    Created on : 22-Nov-2018, 11:44:04
    Author     : Sean
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Drivers</title>
    </head>
    <body>
        <h1>Book Demands</h1>
    </body>
    <body>
        <h2>Demands</h2>
        <%=(String)(request.getAttribute("query"))%>
    </body>
    <body>
        <h2>Journeys</h2>
        <%=(String)(request.getAttribute("query1"))%>
    </body>
    <body>
        <h2>Drivers</h2>
        <%=(String)(request.getAttribute("query2"))%>
    </body>
    <body>
        <h2>Book taxi</h2>
        <form method="POST" action="BookDemands.do">     
            <table>
                <tr>
                    <th></th>
                    <th>Please provide your following details</th>
                </tr>
                <tr>
                    <td>Customer Name:</td>
                    <td><input type="text" name="name"/></td>
                </tr>
                <tr>
                    <td>Address:</td>
                    <td><input type="text" name="address"/></td>
                </tr>
                <tr>
                    <td>Destination:</td>
                    <td><input type="text" name="destination"/></td>
                </tr>
                <tr>
                    <td>Date:</td>
                    <td>
                        <span>
                        <label for="year">Year:</label>
                        <select id="year" name="year">
                        <option>2018</option>
                        <option>2019</option>
                        <option>2020</option>
                        <option>2021</option>
                        <option>2022</option>
                        </select>
                        </span>
                        
                        <span>
                        <label for="month">Month:</label>
                        <select id="month" name="month">
                        <option>01</option>
                        <option>02</option>
                        <option>03</option>
                        <option>04</option>
                        <option>05</option>
                        <option>06</option>
                        <option>07</option>
                        <option>08</option>
                        <option>09</option>
                        <option>10</option>
                        <option Selected>11</option>
                        <option>12</option>
                        </select>
                        </span>
                        
                        <span>
                        <label for="day">Day:</label>
                        <select id="day" name="day">
                        <option selected>01</option>
                        <option>02</option>
                        <option>03</option>
                        <option>04</option>
                        <option>05</option>
                        <option>06</option>
                        <option>07</option>
                        <option>08</option>
                        <option>09</option>
                        <option>10</option>
                        <option>11</option>
                        <option>12</option>
                        <option>13</option>
                        <option>14</option>
                        <option>15</option>
                        <option>16</option>
                        <option>17</option>
                        <option>18</option>
                        <option>19</option>
                        <option>20</option>
                        <option>21</option>
                        <option>22</option>
                        <option>23</option>
                        <option>24</option>
                        <option>25</option>
                        <option>26</option>
                        <option>27</option>
                        <option>28</option>
                        <option>29</option>
                        <option>30</option>
                        <option>31</option>
                        
                        </select>
                        </span>
                    </td>
                    
                </tr>
                <tr>
                    <td>Time:</td>
                    <td><input type="text" name="time"/></td>
                </tr>
                <tr>
                    <td>Driver Registration:</td>
                    <td><input type="text" name="registration"/></td>
                </tr>
                <tr> 
                    <td> <input type="submit" value="Book"/></td>
                </tr>
            </table>
        </form>
        <%=((String) (request.getAttribute("msg")) != null) ? (String) (request.getAttribute("msg")) : ""%>
        <jsp:include page="foot.jsp"/>
    </body>