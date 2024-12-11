variable "name" {
  default     = "karaplan"
  description = "Name to use in all resources of this module"
}
variable "credentials" {
  description = "Service account credentials JSON file"
}
variable "project_id" {
  description = "GCP project ID"
}
variable "region" {
  description = "GCP region"
}
variable "dns_project_id" {
  default     = ""
  description = "Cloud DNS project ID"
}
variable "dns_zone" {
  default     = ""
  description = "Cloud DNS zone name"
}
variable "gke_cluster_name" {
  description = "GKE cluster name"
}
variable "gke_namespace" {
  default     = "default"
  description = "GKE namespace (must exist)"
}
variable "http_enabled" {
  default     = true
  description = "Enable HTTP load balancing"
}
variable "https_enabled" {
  default     = true
  description = "Enable HTTPS load balancing"
}
variable "replica_count" {
  default     = 3
  description = "Deployment replica count"
}
variable "google_oauth_clientid" {
  description = "Google OAuth 2.0 client ID"
}
variable "google_oauth_clientsecret" {
  description = "Google OAuth 2.0 client secret"
}
variable "github_oauth_clientid" {
  description = "GitHub OAuth 2.0 client ID"
}
variable "github_oauth_clientsecret" {
  description = "GitHub OAuth 2.0 client secret"
}
