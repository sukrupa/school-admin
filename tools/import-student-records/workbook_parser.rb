require 'worksheet_parser'

class WorkbookParser
  
  def parse(workbook)
    students_and_talents_array = []
    workbook.sheets.each do |sheet_name|
      workbook.default_sheet = sheet_name
      students_and_talents_array += WorksheetParser.new(workbook).parse
    end
    return students_and_talents_array
  end
  
end

