<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="dnf.Battle, dnf.Character, dnf.Warrior, dnf.Mage, dnf.Player, dnf.Dragon" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%
    Character c     = (Character) session.getAttribute("character");
    String playerId = (String) session.getAttribute("playerId");
    Dragon dragon   = (Dragon) session.getAttribute("dragon");
    String result   = null;

    if (c == null) {
        response.sendRedirect("createCharacter.jsp");
        return;
    }

    if (dragon == null) {
        dragon = new Dragon();
        session.setAttribute("dragon", dragon);
    }

    if ("POST".equalsIgnoreCase(request.getMethod())) {
        Battle battle = new Battle();
        result = battle.attackMonster(playerId, c, dragon);
        session.setAttribute("dragon", dragon);
    }

    int hpPercent = dragon.getHpPercent();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>몬스터 공격 - 던전앤파이터</title>
</head>
<body>
<h2>[ 몬스터 공격 ]</h2>
<hr>

<h3>[ 내 캐릭터 ]</h3>
<p><b>플레이어 ID:</b> <%= playerId %></p>
<p><b>캐릭터명:</b> <%= c.getCharName() %></p>
<p><b>직업:</b> <%= c.getJob() %></p>
<p><b>레벨:</b> <%= c.getLevel() %></p>
<p><b>HP:</b> <%= c.getHp() %></p>
<p><b>공격력:</b> <%= (int) c.getAttack() %></p>

<hr>

<h3>[ 붉은 용 ]</h3>
<p><b>HP:</b> <%= dragon.getCurrentHp() %> / <%= dragon.getMaxHp() %></p>
<p>
    [
    <%
        int blocks = hpPercent / 5;
        for (int i = 0; i < 20; i++) {
            if (i < blocks) out.print("█");
            else out.print("░");
        }
    %>
    ] <%= hpPercent %>%
</p>

<hr>

<% if (dragon.isAlive()) { %>
    <form method="POST" action="attackMonster.jsp">
        <input type="submit" value="몬스터 공격!" />
    </form>
<% } else { %>
    <p><b>★ 용을 처치했습니다! 클리어! ★</b></p>
    <a href="createCharacter.jsp"><button>다시 시작</button></a>
<% } %>

<hr>

<% if (result != null) { %>
    <p><b>공격 결과:</b> <%= result %></p>
<% } %>

<br>
<a href="createCharacter.jsp">← 캐릭터 생성으로</a>
&nbsp;&nbsp;
<a href="index.jsp">← 메인으로</a>
</body>
</html>
