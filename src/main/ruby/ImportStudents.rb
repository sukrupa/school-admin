require 'rubygems'
require 'roo'
require 'WorksheetParser'
require 'Student'


class ImportStudents
  students = Excelx.new(ARGV[0])
  students.default_sheet = students.sheets.first
  row_manager = WorksheetParser.new(students)
  puts row_manager.starting_corner
  puts row_manager.skipped_columns
  puts row_manager.parse
end
