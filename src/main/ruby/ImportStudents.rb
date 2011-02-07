require 'rubygems'
require 'roo'
require 'WorksheetParser'
require 'Student'
require 'sql_generator'

students_excel = Excelx.new(ARGV[0])
sheet_names = students_excel.sheets

File.open('import_students.sql', 'w') { |file|
  sheet_names.each do |sheet_name|
    file.puts(sheet_name) 
    students_excel.default_sheet = sheet_name
    row_manager = WorksheetParser.new(students_excel)
    students = row_manager.parse
    SQLGenerator.new.generate_sql(file, students)
  end
}
