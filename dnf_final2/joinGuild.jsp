<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="dnf.Battle, dnf.Character, dnf.Guild, java.util.List" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%
    Character c     = (Character) session.getAttribute("character");
    String playerId = (String)    session.getAttribute("playerId");
    String result   = null;

    if (c == null) {
        response.sendRedirect("createCharacter.jsp");
        return;
    }

    // Aggregation: 길드는 독립적으로 존재 (세션에 보관)
    Guild guild = (Guild) session.getAttribute("guild");

    if ("POST".equalsIgnoreCase(request.getMethod())) {
        String action  = request.getParameter("action");
        String gName   = request.getParameter("길드명");

        if ("create".equals(action) && gName != null && !gName.trim().isEmpty()) {
            guild = new Guild(gName.trim());
            session.setAttribute("guild", guild);
            result = "길드 [" + guild.get길드명() + "] 생성 완료!";
        } else if ("join".equals(action) && guild != null) {
            Battle battle = new Battle();
            result = battle.joinGuild(playerId, c, guild);
            session.setAttribute("guild", guild);
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>길드 가입 - 던전앤파이터</title>
    <style>
        body { font-family: sans-serif; padding: 20px; }
        table { border-collapse: collapse; }
        th, td { border: 1px solid #ccc; padding: 8px 12px; text-align: left; }
        th { background: #f0f0f0; }
        .result-box { background: #e8f5e9; border: 1px solid #4caf50; padding: 10px; margin: 10px 0; border-radius: 4px; }
        .fail-box   { background: #fdecea; border: 1px solid #f44336; padding: 10px; margin: 10px 0; border-radius: 4px; }
        .section { background: #f9f9f9; border: 1px solid #ddd; padding: 14px; margin: 12px 0; border-radius: 4px; }
    </style>
</head>
<body>
<h2>[ 길드 가입 ]</h2>
<hr>

<h3>[ 내 캐릭터 ]</h3>
<p><b>캐릭터명:</b> <%= c.getCharName() %> | <b>직업:</b> <%= c.getJob() %> | <b>레벨:</b> <%= c.getLevel() %></p>

<hr>

<div class="section">
<h3>[ 길드 생성 ]</h3>
<form method="POST" action="joinGuild.jsp">
    <input type="hidden" name="action" value="create" />
    <input type="text" name="길드명" placeholder="예) 불꽃기사단" required />
    <input type="submit" value="<%= guild != null ? "새 길드 만들기" : "길드 생성" %>" />
</form>
<% if (guild != null) { %>
    <p>현재 길드: <b>[<%= guild.get길드명() %>]</b> (<%= guild.get현재인원() %>/<%= guild.get최대인원() %>명)</p>
<% } %>
</div>

<div class="section">
<h3>[ 길드 가입 ]</h3>
<% if (guild == null) { %>
    <p>먼저 길드를 생성해 주세요.</p>
<% } else if (guild.isFull()) { %>
    <p style="color:red;"><b>⚠ 길드 정원이 가득 찼습니다! (5/5)</b></p>
<% } else { %>
    <p>길드 <b>[<%= guild.get길드명() %>]</b> 에 <b><%= c.getCharName() %></b> 을(를) 가입시킵니다.</p>
    <form method="POST" action="joinGuild.jsp">
        <input type="hidden" name="action" value="join" />
        <input type="submit" value="길드 가입!" />
    </form>
<% } %>
</div>

<% if (result != null) { %>
    <div class="<%= (result.contains("성공") || result.contains("완료")) ? "result-box" : "fail-box" %>">
        <b>결과:</b> <%= result %>
    </div>
<% } %>

<% if (guild != null) { %>
<hr>
<h3>[ 길드 현황 ] <%= guild.get길드명() %> — <%= guild.get현재인원() %> / <%= guild.get최대인원() %>명</h3>
<% List<Character> members = guild.get캐릭터리스트(); %>
<% if (members.isEmpty()) { %>
    <p>아직 길드원이 없습니다.</p>
<% } else { %>
    <table>
        <tr><th>#</th><th>캐릭터명</th><th>직업</th><th>레벨</th></tr>
        <% for (int i = 0; i < members.size(); i++) {
               Character m = members.get(i); %>
        <tr>
            <td><%= i + 1 %></td>
            <td><%= m.getCharName() %></td>
            <td><%= m.getJob() %></td>
            <td><%= m.getLevel() %></td>
        </tr>
        <% } %>
    </table>
<% } %>
<% } %>

<br>
<a href="addItem.jsp"><button>아이템 획득으로 이동</button></a>
&nbsp;
<a href="attackMonster.jsp"><button>몬스터 공격으로 이동</button></a>
&nbsp;
<a href="index.jsp">← 메인으로</a>
</body>
</html>
