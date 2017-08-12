FROM logstash:5
RUN logstash-plugin install logstash-input-log4j2
COPY logstash.conf /some/config-dir/
CMD ["-f", "/some/config-dir/logstash.conf"]