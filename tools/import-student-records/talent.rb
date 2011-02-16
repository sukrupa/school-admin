class Talent
  
  attr_reader :talents
  
  def initialize comma_separated_talents
    if (!comma_separated_talents.nil? and !comma_separated_talents.strip.empty?)
      @talents = comma_separated_talents.split(",")
      @talents = @talents.map do |talent|
        talent.strip
        if talent.match("Art")
            talent = "Arts & Crafts"
        elsif talent.match("Music")
            talent = "Musical Instrument"
        elsif talent.match("Danc")
            talent = "Dancing"
        elsif talent.match("Sing")
            talent = "Singing"
        elsif talent.match("Sports")
            talent = "Sports"
        elsif talent.match("Draw")
            talent = "Drawing"
        elsif talent.match("Writing")
            talent = "Creative Writing"
        elsif talent.match("Mimic")
            talent = "Mimicry"
        elsif talent.match("Act")
            talent = "Acting"
        elsif talent.match("Pick")
            talent = "Pick & Speak"
        elsif talent.match("Read")
            talent = "Reading"
        elsif talent.match("Story")
            talent = "Story Telling"
        elsif talent.match("Public")
            talent = "Public Speaking"
        end
      end
    else
      @talents = []
    end
  end
  
end
