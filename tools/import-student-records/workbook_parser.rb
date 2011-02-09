require 'worksheet_parser'

class WorkbookParser
  
  def parse(workbook)
    students = []
    workbook.sheets.each do |sheet_name|
      workbook.default_sheet = sheet_name
      students += WorksheetParser.new(workbook).parse
    end
    return students
  end
  
end

