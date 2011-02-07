require 'rubygems'
require 'roo'
require 'worksheet_parser'
require 'student  '
require 'sql_generator'

students_excel = Excelx.new(ARGV[0])
sheet_names = students_excel.sheets
sql_generator = SQLGenerator.new

File.open('import_students.sql', 'w') { |file|
  sheet_names.each do |sheet_name|
    file.puts(sheet_name) 
    students_excel.default_sheet = sheet_name
    worksheet_parser = WorksheetParser.new(students_excel)
    students = worksheet_parser.parse
    sql_generator.generate_sql(file, students)
  end
}
