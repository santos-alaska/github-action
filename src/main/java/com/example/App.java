package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class App {

    @GetMapping("/")
    public String home() {
        return """
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DevOps Tools Reference</title>
    <link href="https://fonts.googleapis.com/css2?family=JetBrains+Mono:wght@400;700&family=Syne:wght@400;700;800&display=swap" rel="stylesheet">
    <style>
        *, *::before, *::after { margin: 0; padding: 0; box-sizing: border-box; }

        :root {
            --bg: #0e0e0e;
            --surface: #161616;
            --border: #2a2a2a;
            --text: #e8e8e8;
            --muted: #666;
            --accent-ci: #f97316;
            --accent-container: #3b82f6;
            --accent-k8s: #a78bfa;
            --accent-iac: #34d399;
            --accent-monitor: #fb7185;
            --accent-scm: #fbbf24;
            --mono: 'JetBrains Mono', monospace;
            --sans: 'Syne', sans-serif;
        }

        body {
            background: var(--bg);
            color: var(--text);
            font-family: var(--sans);
            min-height: 100vh;
            overflow-x: hidden;
        }

        /* --- HEADER --- */
        header {
            border-bottom: 1px solid var(--border);
            padding: 28px 40px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            position: sticky;
            top: 0;
            background: rgba(14,14,14,0.92);
            backdrop-filter: blur(12px);
            z-index: 100;
        }

        .logo {
            font-family: var(--mono);
            font-size: 1rem;
            color: var(--muted);
            letter-spacing: 0.08em;
        }

        .logo span { color: var(--accent-iac); }

        .header-title {
            font-size: 0.8rem;
            font-family: var(--mono);
            color: var(--muted);
            text-transform: uppercase;
            letter-spacing: 0.15em;
        }

        /* --- HERO --- */
        .hero {
            padding: 100px 40px 80px;
            max-width: 1100px;
            margin: 0 auto;
        }

        .hero-tag {
            font-family: var(--mono);
            font-size: 0.75rem;
            color: var(--accent-iac);
            text-transform: uppercase;
            letter-spacing: 0.2em;
            margin-bottom: 24px;
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .hero-tag::before {
            content: '';
            display: inline-block;
            width: 28px;
            height: 1px;
            background: var(--accent-iac);
        }

        h1 {
            font-size: clamp(2.8rem, 6vw, 5rem);
            font-weight: 800;
            line-height: 1.05;
            letter-spacing: -0.03em;
            margin-bottom: 24px;
        }

        h1 em {
            font-style: normal;
            color: var(--muted);
        }

        .hero-desc {
            font-family: var(--mono);
            font-size: 0.95rem;
            color: var(--muted);
            max-width: 520px;
            line-height: 1.8;
        }

        /* --- FILTER BAR --- */
        .filter-bar {
            max-width: 1100px;
            margin: 0 auto 60px;
            padding: 0 40px;
            display: flex;
            gap: 10px;
            flex-wrap: wrap;
        }

        .filter-btn {
            font-family: var(--mono);
            font-size: 0.75rem;
            text-transform: uppercase;
            letter-spacing: 0.1em;
            padding: 8px 18px;
            border-radius: 3px;
            border: 1px solid var(--border);
            background: transparent;
            color: var(--muted);
            cursor: pointer;
            transition: all 0.2s;
        }

        .filter-btn:hover, .filter-btn.active {
            background: var(--text);
            color: var(--bg);
            border-color: var(--text);
        }

        /* --- GRID --- */
        .grid {
            max-width: 1100px;
            margin: 0 auto;
            padding: 0 40px 100px;
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
            gap: 1px;
            background: var(--border);
            border: 1px solid var(--border);
        }

        .tool-card {
            background: var(--surface);
            padding: 32px 28px;
            transition: background 0.2s;
            cursor: default;
            position: relative;
            overflow: hidden;
        }

        .tool-card::after {
            content: '';
            position: absolute;
            bottom: 0; left: 0;
            width: 100%; height: 2px;
            transform: scaleX(0);
            transform-origin: left;
            transition: transform 0.3s ease;
        }

        .tool-card:hover { background: #1c1c1c; }
        .tool-card:hover::after { transform: scaleX(1); }

        /* category accent colors for bottom bar */
        .tool-card[data-cat="ci"]::after    { background: var(--accent-ci); }
        .tool-card[data-cat="container"]::after { background: var(--accent-container); }
        .tool-card[data-cat="k8s"]::after   { background: var(--accent-k8s); }
        .tool-card[data-cat="iac"]::after   { background: var(--accent-iac); }
        .tool-card[data-cat="monitor"]::after { background: var(--accent-monitor); }
        .tool-card[data-cat="scm"]::after   { background: var(--accent-scm); }

        .card-top {
            display: flex;
            align-items: flex-start;
            justify-content: space-between;
            margin-bottom: 20px;
        }

        .tool-icon {
            font-size: 2rem;
            line-height: 1;
        }

        .cat-badge {
            font-family: var(--mono);
            font-size: 0.65rem;
            text-transform: uppercase;
            letter-spacing: 0.12em;
            padding: 4px 10px;
            border-radius: 2px;
            font-weight: 700;
        }

        [data-cat="ci"]      .cat-badge { background: rgba(249,115,22,0.12); color: var(--accent-ci); }
        [data-cat="container"] .cat-badge { background: rgba(59,130,246,0.12); color: var(--accent-container); }
        [data-cat="k8s"]     .cat-badge { background: rgba(167,139,250,0.12); color: var(--accent-k8s); }
        [data-cat="iac"]     .cat-badge { background: rgba(52,211,153,0.12); color: var(--accent-iac); }
        [data-cat="monitor"] .cat-badge { background: rgba(251,113,133,0.12); color: var(--accent-monitor); }
        [data-cat="scm"]     .cat-badge { background: rgba(251,191,36,0.12); color: var(--accent-scm); }

        .tool-name {
            font-size: 1.3rem;
            font-weight: 800;
            letter-spacing: -0.02em;
            margin-bottom: 8px;
        }

        .tool-desc {
            font-family: var(--mono);
            font-size: 0.8rem;
            color: var(--muted);
            line-height: 1.7;
            margin-bottom: 20px;
        }

        .tool-tags {
            display: flex;
            flex-wrap: wrap;
            gap: 6px;
        }

        .tag {
            font-family: var(--mono);
            font-size: 0.65rem;
            color: var(--muted);
            border: 1px solid var(--border);
            padding: 3px 8px;
            border-radius: 2px;
        }

        /* --- FOOTER --- */
        footer {
            border-top: 1px solid var(--border);
            padding: 30px 40px;
            text-align: center;
            font-family: var(--mono);
            font-size: 0.75rem;
            color: var(--muted);
        }

        footer span { color: var(--accent-iac); }

        /* --- HIDDEN --- */
        .tool-card.hidden { display: none; }

        @media (max-width: 600px) {
            header, .hero, .filter-bar, .grid { padding-left: 20px; padding-right: 20px; }
            h1 { font-size: 2.4rem; }
            .grid { grid-template-columns: 1fr; }
        }
    </style>
</head>
<body>

<header>
    <div class="logo">$ <span>devops</span>-tools.ref</div>
    <div class="header-title">Quick Reference Guide</div>
</header>

<section class="hero">
    <div class="hero-tag">v1.0 — 2026 Edition</div>
    <h1>DevOps<br><em>Tools</em><br>Reference</h1>
    <p class="hero-desc">
        A quick-access reference to the most widely used tools across the DevOps lifecycle —
        from source control to monitoring.
    </p>
</section>

<div class="filter-bar">
    <button class="filter-btn active" onclick="filter('all', this)">All</button>
    <button class="filter-btn" onclick="filter('ci', this)">CI/CD</button>
    <button class="filter-btn" onclick="filter('container', this)">Containers</button>
    <button class="filter-btn" onclick="filter('k8s', this)">Kubernetes</button>
    <button class="filter-btn" onclick="filter('iac', this)">IaC</button>
    <button class="filter-btn" onclick="filter('monitor', this)">Monitoring</button>
    <button class="filter-btn" onclick="filter('scm', this)">SCM</button>
</div>

<div class="grid" id="grid">

    <div class="tool-card" data-cat="scm">
        <div class="card-top">
            <div class="tool-icon">🐙</div>
            <span class="cat-badge">SCM</span>
        </div>
        <div class="tool-name">GitHub</div>
        <div class="tool-desc">Cloud-hosted Git repository platform with collaboration features, issues, PRs, and Actions for CI/CD automation.</div>
        <div class="tool-tags">
            <span class="tag">git</span><span class="tag">pull-requests</span><span class="tag">actions</span>
        </div>
    </div>

    <div class="tool-card" data-cat="scm">
        <div class="card-top">
            <div class="tool-icon">🦊</div>
            <span class="cat-badge">SCM</span>
        </div>
        <div class="tool-name">GitLab</div>
        <div class="tool-desc">All-in-one DevOps platform with built-in CI/CD pipelines, container registry, and security scanning.</div>
        <div class="tool-tags">
            <span class="tag">git</span><span class="tag">ci-cd</span><span class="tag">self-hosted</span>
        </div>
    </div>

    <div class="tool-card" data-cat="ci">
        <div class="card-top">
            <div class="tool-icon">⚙️</div>
            <span class="cat-badge">CI/CD</span>
        </div>
        <div class="tool-name">GitHub Actions</div>
        <div class="tool-desc">Native CI/CD for GitHub repos. Trigger workflows on push, PR, schedule, or manual dispatch using YAML files.</div>
        <div class="tool-tags">
            <span class="tag">yaml</span><span class="tag">runners</span><span class="tag">secrets</span>
        </div>
    </div>

    <div class="tool-card" data-cat="ci">
        <div class="card-top">
            <div class="tool-icon">🏗️</div>
            <span class="cat-badge">CI/CD</span>
        </div>
        <div class="tool-name">Jenkins</div>
        <div class="tool-desc">Open-source automation server. Highly extensible via plugins. Uses Jenkinsfile (Groovy DSL) for pipeline definitions.</div>
        <div class="tool-tags">
            <span class="tag">groovy</span><span class="tag">plugins</span><span class="tag">self-hosted</span>
        </div>
    </div>

    <div class="tool-card" data-cat="ci">
        <div class="card-top">
            <div class="tool-icon">🔵</div>
            <span class="cat-badge">CI/CD</span>
        </div>
        <div class="tool-name">CircleCI</div>
        <div class="tool-desc">Cloud-native CI/CD with fast pipelines, parallelism, and reusable orbs (pre-packaged integrations).</div>
        <div class="tool-tags">
            <span class="tag">orbs</span><span class="tag">parallelism</span><span class="tag">cloud</span>
        </div>
    </div>

    <div class="tool-card" data-cat="container">
        <div class="card-top">
            <div class="tool-icon">🐳</div>
            <span class="cat-badge">Container</span>
        </div>
        <div class="tool-name">Docker</div>
        <div class="tool-desc">Build, ship, and run containers. Package apps with all dependencies into portable images using a Dockerfile.</div>
        <div class="tool-tags">
            <span class="tag">dockerfile</span><span class="tag">images</span><span class="tag">registry</span>
        </div>
    </div>

    <div class="tool-card" data-cat="container">
        <div class="card-top">
            <div class="tool-icon">📦</div>
            <span class="cat-badge">Container</span>
        </div>
        <div class="tool-name">Docker Compose</div>
        <div class="tool-desc">Define and run multi-container applications with a single YAML file. Great for local dev environments.</div>
        <div class="tool-tags">
            <span class="tag">yaml</span><span class="tag">multi-container</span><span class="tag">networking</span>
        </div>
    </div>

    <div class="tool-card" data-cat="container">
        <div class="card-top">
            <div class="tool-icon">🗂️</div>
            <span class="cat-badge">Container</span>
        </div>
        <div class="tool-name">DockerHub</div>
        <div class="tool-desc">Public container image registry. Push and pull images, set up automated builds, and store private repos.</div>
        <div class="tool-tags">
            <span class="tag">registry</span><span class="tag">public</span><span class="tag">private</span>
        </div>
    </div>

    <div class="tool-card" data-cat="k8s">
        <div class="card-top">
            <div class="tool-icon">☸️</div>
            <span class="cat-badge">Kubernetes</span>
        </div>
        <div class="tool-name">Kubernetes</div>
        <div class="tool-desc">Open-source container orchestration. Automates deployment, scaling, and management of containerized workloads.</div>
        <div class="tool-tags">
            <span class="tag">pods</span><span class="tag">deployments</span><span class="tag">services</span>
        </div>
    </div>

    <div class="tool-card" data-cat="k8s">
        <div class="card-top">
            <div class="tool-icon">☁️</div>
            <span class="cat-badge">Kubernetes</span>
        </div>
        <div class="tool-name">AWS EKS</div>
        <div class="tool-desc">Managed Kubernetes service on AWS. Handles control plane, upgrades, and integrates with IAM, ALB, and EBS.</div>
        <div class="tool-tags">
            <span class="tag">aws</span><span class="tag">managed</span><span class="tag">iam</span>
        </div>
    </div>

    <div class="tool-card" data-cat="k8s">
        <div class="card-top">
            <div class="tool-icon">🎡</div>
            <span class="cat-badge">Kubernetes</span>
        </div>
        <div class="tool-name">Helm</div>
        <div class="tool-desc">The package manager for Kubernetes. Bundle K8s manifests into reusable charts with templating and versioning.</div>
        <div class="tool-tags">
            <span class="tag">charts</span><span class="tag">templates</span><span class="tag">releases</span>
        </div>
    </div>

    <div class="tool-card" data-cat="k8s">
        <div class="card-top">
            <div class="tool-icon">🔄</div>
            <span class="cat-badge">Kubernetes</span>
        </div>
        <div class="tool-name">ArgoCD</div>
        <div class="tool-desc">GitOps continuous delivery tool for Kubernetes. Syncs cluster state with Git repo definitions automatically.</div>
        <div class="tool-tags">
            <span class="tag">gitops</span><span class="tag">sync</span><span class="tag">declarative</span>
        </div>
    </div>

    <div class="tool-card" data-cat="iac">
        <div class="card-top">
            <div class="tool-icon">🏛️</div>
            <span class="cat-badge">IaC</span>
        </div>
        <div class="tool-name">Terraform</div>
        <div class="tool-desc">Infrastructure as Code tool by HashiCorp. Provision cloud resources using HCL. Supports AWS, GCP, Azure and more.</div>
        <div class="tool-tags">
            <span class="tag">hcl</span><span class="tag">state</span><span class="tag">plan/apply</span>
        </div>
    </div>

    <div class="tool-card" data-cat="iac">
        <div class="card-top">
            <div class="tool-icon">📜</div>
            <span class="cat-badge">IaC</span>
        </div>
        <div class="tool-name">Ansible</div>
        <div class="tool-desc">Agentless configuration management and automation tool. Uses YAML playbooks to configure servers over SSH.</div>
        <div class="tool-tags">
            <span class="tag">playbooks</span><span class="tag">agentless</span><span class="tag">ssh</span>
        </div>
    </div>

    <div class="tool-card" data-cat="monitor">
        <div class="card-top">
            <div class="tool-icon">🔥</div>
            <span class="cat-badge">Monitoring</span>
        </div>
        <div class="tool-name">Prometheus</div>
        <div class="tool-desc">Open-source metrics collection and alerting system. Scrapes targets at intervals and stores time-series data.</div>
        <div class="tool-tags">
            <span class="tag">metrics</span><span class="tag">alerts</span><span class="tag">promql</span>
        </div>
    </div>

    <div class="tool-card" data-cat="monitor">
        <div class="card-top">
            <div class="tool-icon">📊</div>
            <span class="cat-badge">Monitoring</span>
        </div>
        <div class="tool-name">Grafana</div>
        <div class="tool-desc">Visualization platform for metrics and logs. Build dashboards from Prometheus, CloudWatch, Loki, and more.</div>
        <div class="tool-tags">
            <span class="tag">dashboards</span><span class="tag">panels</span><span class="tag">alerts</span>
        </div>
    </div>

    <div class="tool-card" data-cat="monitor">
        <div class="card-top">
            <div class="tool-icon">🪵</div>
            <span class="cat-badge">Monitoring</span>
        </div>
        <div class="tool-name">ELK Stack</div>
        <div class="tool-desc">Elasticsearch, Logstash & Kibana. Collect, parse, store, and visualize logs at scale across distributed systems.</div>
        <div class="tool-tags">
            <span class="tag">logs</span><span class="tag">search</span><span class="tag">kibana</span>
        </div>
    </div>

    <div class="tool-card" data-cat="iac">
        <div class="card-top">
            <div class="tool-icon">☁️</div>
            <span class="cat-badge">IaC</span>
        </div>
        <div class="tool-name">AWS CloudFormation</div>
        <div class="tool-desc">AWS-native IaC. Define stacks with JSON/YAML templates to provision and manage AWS resources declaratively.</div>
        <div class="tool-tags">
            <span class="tag">stacks</span><span class="tag">yaml</span><span class="tag">aws-native</span>
        </div>
    </div>

</div>

<footer>
    Built with <span>Spring Boot</span> · DevOps Tools Reference · 2026
</footer>

<script>
    function filter(cat, btn) {
        document.querySelectorAll('.filter-btn').forEach(b => b.classList.remove('active'));
        btn.classList.add('active');
        document.querySelectorAll('.tool-card').forEach(card => {
            if (cat === 'all' || card.dataset.cat === cat) {
                card.classList.remove('hidden');
            } else {
                card.classList.add('hidden');
            }
        });
    }
</script>
</body>
</html>
        """;
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}