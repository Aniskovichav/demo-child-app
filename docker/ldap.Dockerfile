FROM osixia/openldap:1.5.0

ENV LDAP_ORGANISATION="Example Organization"
ENV LDAP_DOMAIN="example.com"
ENV LDAP_ADMIN_PASSWORD=admin

COPY ./ldap-config /container/service/slapd/assets/config/bootstrap/ldif/custom/
EXPOSE 389
