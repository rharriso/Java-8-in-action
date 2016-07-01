#
# batch run jar file
#

puts "appleCount, numberPassed, elapsed0, elapsed1, elapsed0 - elapsed1"

(0...20).each do |i|
  10.times do
    puts `java -jar ./out/artifacts/FilterBench_jar/FilterBench.jar #{i}`
  end
end
