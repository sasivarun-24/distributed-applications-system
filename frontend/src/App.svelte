<script>
  import { onMount } from 'svelte';
  let products = [];
  let error = null;
  let tenantId = 'shop-123'; // Default tenant (SALE)
  const catalogUrl = 'http://localhost:8080/saas/catalog';

  async function fetchProducts() {
    error = null;
    products = [];
    try {
      const response = await fetch(catalogUrl, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          'X-API-KEY': 'YOUR_SECRET_DEFINED_IN_PROPERTIES',
          'X-TENANT-ID': tenantId
        }
      });

      if (!response.ok) {
        throw new Error(`Error fetching products: ${response.status} ${response.statusText}`);
      }

      products = await response.json();
    } catch (err) {
      error = err.message;
    }
  }

  function setTenant(id) {
    tenantId = id;
    fetchProducts();
  }

  onMount(() => {
    fetchProducts();
  });
</script>

<main>
  <h1>Product Catalog</h1>

  <div class="controls">
      <button on:click={() => setTenant('shop-123')} class:active={tenantId === 'shop-123'}>Shop 123 (Sale)</button>
      <button on:click={() => setTenant('shop-abc')} class:active={tenantId === 'shop-abc'}>Shop ABC (Standard)</button>
      <button on:click={() => setTenant('invalid')} class:active={tenantId === 'invalid'}>Invalid Tenant</button>
  </div>

  {#if error}
    <p class="error">{error}</p>
  {:else if products.length === 0}
    <p>No products found or loading...</p>
  {:else}
    <div>
      {#each products as product}
        <div class="product-row">
            <span><strong>{product.name}</strong></span>
            <span>{product.color}</span>
            <span>{product.size}</span>
            <span>{product.price}€</span>
            <span class="badge">{product.category}</span>
        </div>
      {/each}
    </div>
  {/if}
</main>

<style>
  main {
    padding: 50px;
    font-family: sans-serif;
  }
  .error {
    color: red;
  }
  .product-row {
      display: flex;
      gap: 15px;
      margin-bottom: 10px;
      align-items: center;
      padding: 10px;
      border-bottom: 1px solid #eee;
  }
  .controls {
      margin-bottom: 20px;
  }
  button {
      padding: 8px 16px;
      margin-right: 10px;
      cursor: pointer;
  }
  button.active {
      background-color: #007bff;
      color: white;
      border-color: #0056b3;
  }
  .badge {
      background: #eee;
      padding: 2px 8px;
      border-radius: 4px;
      font-size: 0.8em;
  }
</style>
