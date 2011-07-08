package org.sukrupa.student;

public class StudentProfile {

    private String studentName;
    private String dateOfBirth;
    private String gender;
    private String background;
    private String disciplinary;
    private String comments;

    public StudentProfile(String studentName, String dateOfBirth, String gender, String background, String disciplinary, String comments) {

        this.studentName = studentName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.background = background;
        this.disciplinary = disciplinary;
        this.comments = comments;
    }

    public String composeHtmlMessage() {
        StringBuilder htmlMessage = new StringBuilder();
        htmlMessage.append("<html>");
        htmlMessage.append("<body>");
        htmlMessage.append("<table border=\"0\">");
        createTableRow(htmlMessage, "Name", studentName);
        createTableRow(htmlMessage, "Date Of Birth", dateOfBirth);
        createTableRow(htmlMessage, "Gender", gender);
        createTableRow(htmlMessage, "Background", background);
        createTableRow(htmlMessage, "Disciplinary", disciplinary);
        htmlMessage.append("<tr><td colspan=\"2\">"+comments+"</td></tr>");
        htmlMessage.append("</table>");
        htmlMessage.append("</body>");
        htmlMessage.append("</html>");
        return htmlMessage.toString();
    }

    private void createTableRow(StringBuilder htmlMessage, String attributeName, String value) {
        htmlMessage.append("<tr><td>"+attributeName+"</td><td>");
        htmlMessage.append(value);
        htmlMessage.append("</td></tr>");
    }
}
