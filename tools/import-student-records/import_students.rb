require 'rubygems'
require 'roo'
require 'workbook_parser'
require 'student'
require 'sql_generator'

TARGET_SQL_FILE = '../../target/import_students.sql'

workbook = Excelx.new(ARGV[0])
students = WorkbookParser.new.parse(workbook)
sql_statements = SQLGenerator.new.generate_sql students

File.open(TARGET_SQL_FILE, 'w') { |file| file.puts sql_statements }
puts "Wrote #{sql_statements.length} lines to '#{TARGET_SQL_FILE}'."
