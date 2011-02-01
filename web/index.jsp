<%@ page import="java.io.*"  %>
<html>
<head>
    <title>Sukrupa App</title>
</head>
<body>
<%
String fileName=getServletContext().getRealPath("build-number.txt");

File f=new File(fileName);

InputStream in = new FileInputStream(f);

BufferedInputStream bin = new BufferedInputStream(in);

DataInputStream din = new DataInputStream(bin);

out.println(din.readLine());
%> 
</body>
</html>
