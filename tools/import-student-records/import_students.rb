require 'rubygems'
require 'roo'
require 'workbook_parser'
require 'student'
require 'caregiver'
require 'sql_generator'
  
TARGET_SQL_FILE = '../../ops/deploy/sql/insert-fake-data.sql'

workbook = Excelx.new(ARGV[0])
students_and_talents_array = WorkbookParser.new.parse(workbook)
sql_statements = SQLGenerator.new.generate_sql students_and_talents_array
File.open(TARGET_SQL_FILE, 'w') { |file| file.puts sql_statements }
puts "Wrote #{sql_statements.length} lines to '#{TARGET_SQL_FILE}'."
