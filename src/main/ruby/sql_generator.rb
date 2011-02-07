
require 'rubygems'

class SQLGenerator
  
  def generate_sql(student)
      "INSERT INTO student (name) VALUES ('#{student.name}');"
  end
  
end
