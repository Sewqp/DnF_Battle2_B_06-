<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="dnf.Battle, dnf.Character, dnf.Inventory, dnf.Item, java.util.List" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%
    Character c     = (Character) session.getAttribute("character");
    String playerId = (String)    session.getAttribute("playerId");
    String result   = null;

    if (c == null) {
        response.sendRedirect("createCharacter.jsp");
        return;
    }

    if ("POST".equalsIgnoreCase(request.getMethod())) {
        String itemName = request.getParameter("아이템명");
        String type     = request.getParameter("타입");
        String valueStr = request.getParameter("가치");
        int value = 0;
        try { value = Integer.parseInt(valueStr); } catch (Exception e) {}

        Battle battle = new Battle();
        result = battle.addItem(playerId, c, itemName, type, value);
        session.setAttribute("character", c);
    }

    Inventory inv     = c.get인벤토리();
    List<Item> items  = inv.get아이템리스트();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>아이템 획득 - 던전앤파이터</title>
    <style>
        body { font-family: sans-serif; padding: 20px; }
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ccc; padding: 8px 12px; text-align: left; }
        th { background: #f0f0f0; }
        .legendary { color: #ff6600; font-weight: bold; }
        .rare      { color: #9900cc; font-weight: bold; }
        .common    { color: #333; }
        .result-box { background: #e8f5e9; border: 1px solid #4caf50; padding: 10px; margin: 10px 0; border-radius: 4px; }
        .fail-box   { background: #fdecea; border: 1px solid #f44336; padding: 10px; margin: 10px 0; border-radius: 4px; }
    </style>
</head>
<body>
<h2>[ 아이템 획득 ]</h2>
<hr>

<h3>[ 내 캐릭터 ]</h3>
<p><b>캐릭터명:</b> <%= c.getCharName() %> | <b>직업:</b> <%= c.getJob() %> | <b>레벨:</b> <%= c.getLevel() %></p>

<hr>
<h3>[ 아이템 획득 ]</h3>
<% if (inv.isFull()) { %>
    <p style="color:red;"><b>⚠ 인벤토리가 가득 찼습니다! (10/10)</b></p>
<% } else { %>
<form method="POST" action="addItem.jsp">
    <table style="width:auto;">
        <tr>
            <td>아이템명</td>
            <td><input type="text" name="아이템명" placeholder="예) 불꽃검" required /></td>
        </tr>
        <tr>
            <td>타입</td>
            <td>
                <select name="타입">
                    <option value="무기">무기</option>
                    <option value="방어구">방어구</option>
                    <option value="물약">물약</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>아이템 가치</td>
            <td>
                <input type="number" name="가치" value="500" min="1" />
                <small> ※ 1000이상 전설 / 500이상 희귀 / 500미만 일반</small>
            </td>
        </tr>
    </table>
    <br>
    <input type="submit" value="아이템 획득" />
</form>
<% } %>

<% if (result != null) { %>
    <div class="<%= result.contains("성공") ? "result-box" : "fail-box" %>">
        <b>결과:</b> <%= result %>
    </div>
<% } %>

<hr>
<h3>[ 인벤토리 현황 ] <%= inv.get현재수량() %> / <%= inv.get최대용량() %></h3>
<% if (items.isEmpty()) { %>
    <p>인벤토리가 비어 있습니다.</p>
<% } else { %>
    <table>
        <tr><th>#</th><th>아이템명</th><th>타입</th><th>가치</th><th>등급</th></tr>
        <%
            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);
                String cls = item.get등급().contains("전설") ? "legendary"
                           : item.get등급().contains("희귀") ? "rare" : "common";
        %>
        <tr>
            <td><%= i + 1 %></td>
            <td><%= item.get아이템명() %></td>
            <td><%= item.get타입() %></td>
            <td><%= item.get가치() %></td>
            <td class="<%= cls %>"><%= item.get등급() %></td>
        </tr>
        <% } %>
    </table>
<% } %>

<br>
<a href="joinGuild.jsp"><button>길드 가입으로 이동</button></a>
&nbsp;
<a href="attackMonster.jsp"><button>몬스터 공격으로 이동</button></a>
&nbsp;
<a href="index.jsp">← 메인으로</a>
</body>
</html>
